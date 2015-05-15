package com.wuxincheng.manage.util;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.PrototypicalNodeFactory;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.http.ConnectionManager;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class ParseHtmlTool {

	public static void main(String[] args) {
		String url = "http://wuxincheng.com.cn/blog/detail?blogId=58";
		Map<String, String> data = parse(url);
		System.out.println("fetch data: " + data);
	}
	
	public static Map<String, String> parse(String url) {
		Map<String, String> data = new HashMap<String, String>();
		
		data.put("news_url", url);
		data.put("news_domain", DomainUtil.getSubDomainName(url));

		NodeList titles = getNodeList(url, "title");
		for (int i = 0; i < titles.size(); i++) {
			TitleTag titleNode = (TitleTag) titles.elementAt(i);
			data.put("news_title", HtmlRegexpUtil.html2Text(titleNode.getTitle()));
		}

		NodeList imgs = getNodeList(url, "img");
		for (int i = 0; i < imgs.size(); i++) {
			ImageTag imgNode = (ImageTag) imgs.elementAt(i);
			String imgurl = imgNode.getImageURL();
			if (StringUtils.isNotBlank(imgurl) && Validation.isUrl(imgurl)
					&& !Validation.isChinese(imgurl)) {
				data.put("news_img_link", imgurl);
				break;
			}
		}
		
		return data;
	}
	
	/**
	 * 通过给定的初始节点集合和指定的匹配tag序列，依次遍历初始节点集合的每一个元素，从每一个初始节点中过滤出相应的唯一一个节点，并把所有的返回数组
	 * 
	 * @param sourceList
	 * @param sequence
	 * @return 根据我们要求而过滤得出的节点
	 */
	public static Node[] getTargetNodeArray(NodeList sourceList,
			String[] sequence) {
		if (sourceList == null || sourceList.size() == 0) {
			return null;
		} else {
			Node[] roots = sourceList.toNodeArray();
			int cNum = roots.length;
			LinkedHashSet<Node> found = new LinkedHashSet<Node>();
			for (int i = 0; i < cNum; i++) {
				Node newNode = findNode(roots[i], sequence);
				if (newNode != null)
					found.add(newNode);
			}
			return (Node[]) found.toArray(new Node[found.size()]);

		}
	}

	/**
	 * 通过给定的初始节点集合和指定的匹配tag序列，依次遍历初始节点集合的每一个元素，从中过滤出相应的所有节点，并把所有的返回数组
	 * 
	 * @param sourceList
	 * @param sequence
	 * @return 根据我们要求而过滤得出的节点
	 */
	public static Node[] getAllTargetNodeArray(NodeList sourceList,
			String[] sequence) {
		if (sourceList == null || sourceList.size() == 0) {
			return null;
		} else {
			Node[] roots = sourceList.toNodeArray();
			int cNum = roots.length;
			LinkedHashSet<Node> found = new LinkedHashSet<Node>();
			for (int i = 0; i < cNum; i++) {
				Node[] newNodes = findNodes(roots[i], sequence);
				if (newNodes != null && newNodes.length != 0)
					for (Node n : newNodes)
						found.add(n);
			}
			return (Node[]) found.toArray(new Node[found.size()]);
		}
	}

	/**
	 * 通过给定的一个初始节点和指定的匹配tag序列，依次遍历初始节点的孩子集合的每一个元素，从中过滤出相应的唯一一个节点，并返回该节点
	 * 
	 * @param sourceList
	 * @param sequence
	 * @return 根据我们要求而过滤得出的节点
	 */
	public static Node getTargetNode(Node sourceNode, String[] sequence) {
		if (sourceNode != null) {
			// System.out.println(" sourceNode not null------ ");
			Node newNode = findNode(sourceNode, sequence);
			if (newNode != null)
				return newNode;
			else {
				// System.out.println("result Node null ------ ");
				return null;
			}
		} else {
			// System.out.println(" sourceNode null------ ");
			return null;
		}
	}

	/**
	 * 通过给定的一个初始节点和指定的匹配tag序列，找出初始节点的子节点中符合要求的所有节点，并把所有的那些相似节点以数组的形式返回
	 * 
	 * @param sourceList
	 * @param sequence
	 * @return 根据我们要求而过滤得出的节点
	 */
	public static Node[] getSimilarNodeArray(Node sourceNode, String[] sequence) {
		if (sourceNode != null) {
			Node[] similarNodes = findNodes(sourceNode, sequence);
			return similarNodes;
		} else {
			return null;
		}
	}

	/**
	 * 通过给定的一个节点，按匹配序列进行查找，返回符合条件的唯一一个节点
	 * 
	 * @param source
	 * @param sequence
	 * @return
	 */
	public static Node findNode(Node source, String[] sequence) {
		Stack<Node> curNode = new Stack<Node>();
		curNode.push(source);
		return matchTags(curNode, sequence);
	}

	/**
	 * 通过给定的一个节点，按匹配序列进行查找，返回符合条件的所有相似节点
	 * 
	 * @param source
	 * @param sequence
	 * @return
	 */
	public static Node[] findNodes(Node source, String[] sequence) {
		Stack<Node> curNode = new Stack<Node>();
		curNode.push(source);
		return matchTags(curNode, sequence, true);
	}

	public static final int FIND_SUB = 0; // 找子节点
	public static final int FIND_SIB = 1; // 找同级节点
	public static final int FIND_END = 2; // 结束

	/**
	 * 本方法必须要求每个初始根节点必须有children。该方法返回符合条件的唯一一个节点
	 * 
	 * @param curNode
	 * @param sequence
	 * @return
	 */
	public static Node matchTags(Stack<Node> curNode, String[] sequence) {
		int state = FIND_SUB; // 开始
		int i = 0; // 记录匹配的tag序号
		int depth = sequence.length; // 记录查找的深度
		int[] index = new int[depth]; // 记录每级匹配的序列索引，即那一级的所有孩子的序列号
		while (state != FIND_END) {
			Node cNode = (Node) curNode.pop(); // 当前节点
			if (state == FIND_SUB) { // 查找子节点
				if (i < depth) {
					// 下面这一步的getChildren可能会报错
					NodeList cList = cNode.getChildren();
					if (cList != null) {
						Node[] subNodes = cNode
								.getChildren()
								.extractAllNodesThatMatch(
										new TagNameFilter(sequence[i]))
								.toNodeArray();
						if (subNodes == null || subNodes.length == 0) { // 没有子节点
							curNode.push(cNode);
							state = FIND_SIB; // 下一次需要找同级节点
						} else {

							curNode.push(cNode);
							curNode.push(subNodes[0]);
							index[i] = 0;// 第i级的当前测试节点索引为0
							i++;
							state = FIND_SUB;
						}
					} else {
						curNode.push(cNode);
						state = FIND_SIB; // 下一次需要找同级节点
					}
				} else if (i == depth) {// 说明已经匹配到设定的深度了，可以取出该节点了
					return cNode;
				}
			} else if (state == FIND_SIB) { // 查找同级节点
				if (curNode.isEmpty()) {
					state = FIND_END; // 已经没有可以找的了，需要退出查找过程，反之栈里面一定含有父节点，所以i>0
				} else {
					Node parentNode = (Node) curNode.peek();
					Node[] sibNodes = parentNode
							.getChildren()
							.extractAllNodesThatMatch(
									new TagNameFilter(sequence[i - 1]))
							.toNodeArray();
					int sibNum = sibNodes.length;
					if (index[i - 1] + 1 < sibNum) { // 存在下一个同级节点
						curNode.push(sibNodes[index[i - 1] + 1]);
						index[i - 1] += 1;
						state = FIND_SUB; // 需要查找子节点
					} else { // 这就是最后一个同级节点,故要返回上一级
						state = FIND_SIB;
						index[i - 1] = 0; // 第i级匹配索引重设为0
						i--;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 本方法必须要求初始根节点必须有children。该方法返回符合条件的一批相似节点
	 * 
	 * @param curNode
	 * @param sequence
	 * @param similar
	 *            为true标识是查找一个相似的序列
	 * @return
	 */
	public static Node[] matchTags(Stack<Node> curNode, String[] sequence,
			Boolean similar) {
		int state = FIND_SUB; // 开始
		int i = 0; // 记录匹配的tag序号
		int depth = sequence.length; // 记录查找的深度
		int[] index = new int[depth]; // 记录每级匹配的序列索引，即那一级的所有孩子的序列号
		LinkedHashSet<Node> found = new LinkedHashSet<Node>(); // 记录查出符合要求的节点数组
		if (true == similar) {
			while (state != FIND_END) {
				Node cNode = (Node) curNode.pop(); // 当前节点
				if (state == FIND_SUB) { // 查找子节点
					if (i < depth - 1) {
						// 下面这一步的getChildren可能会报错，不过又可能不会报错，因为我压进栈的节点都是在那一级符合我的要求的节点，就肯定是有子节点的，除非到了匹配的最后一级最后
						NodeList cList = cNode.getChildren();
						if (cList != null) {
							Node[] subNodes = cList.extractAllNodesThatMatch(
									new TagNameFilter(sequence[i]))
									.toNodeArray();
							if (subNodes == null || subNodes.length == 0) { // 没有子节点
								curNode.push(cNode);
								state = FIND_SIB; // 下一次需要找同级节点
							} else {
								curNode.push(cNode);
								curNode.push(subNodes[0]);
								index[i] = 0; // 第i级的当前测试节点索引为0
								i++; // 进入下一级
								state = FIND_SUB;
							}
						} else {
							curNode.push(cNode);
							state = FIND_SIB; // 下一次需要找同级节点
						}
					} else if (i == depth - 1) {
						NodeList cList = cNode.getChildren();
						if (cList != null) {
							Node[] subNodes = cList.extractAllNodesThatMatch(
									new TagNameFilter(sequence[i]))
									.toNodeArray();
							if (subNodes != null && subNodes.length != 0) { // 有子节点，由于是最后一级，故全部采集
								for (int j = 0; j < subNodes.length; j++)
									found.add(subNodes[j]);
							}
						}
						curNode.push(cNode);
						state = FIND_SIB; // 下一次需要找同级节点
					}
				} else if (state == FIND_SIB) { // 查找同级节点
					if (curNode.isEmpty()) {
						state = FIND_END; // 已经没有可以找的了，需要退出查找过程，反之栈里面一定含有父节点，所以i>0
					} else {
						Node parentNode = (Node) curNode.peek();
						Node[] sibNodes = parentNode
								.getChildren()
								.extractAllNodesThatMatch(
										new TagNameFilter(sequence[i - 1]))
								.toNodeArray();
						int sibNum = sibNodes.length;
						if (index[i - 1] + 1 < sibNum) { // 存在下一个同级节点
							curNode.push(sibNodes[index[i - 1] + 1]);
							index[i - 1] += 1;
							state = FIND_SUB; // 需要查找子节点
						} else { // 这就是最后一个同级节点,故要返回上一级
							state = FIND_SIB;
							index[i - 1] = 0; // 第i级匹配索引重设为0
							i--;
						}
					}
				}
			}
			return (Node[]) found.toArray(new Node[found.size()]);
		}
		return null;
	}

	/**
	 * 根据给定的节点名字、标签属性、标签值提取出符合条件的所有tag节点
	 * 
	 * @param url
	 * @param tagName
	 * @param attributeName
	 * @param attributeValue
	 * @return 符合条件的List
	 */
	public static NodeList getNodeList(String url, String tagName,
			String attributeName, String attributeValue) {
		ConnectionManager manager;
		manager = org.htmlparser.lexer.Page.getConnectionManager();
		Parser parser;
		try {
			parser = new Parser(manager.openConnection(url));
			parser.setEncoding("UTF-8");

			// 下面的节点注册一定要放在最前面，才能把指定节点的所有孩子节点都按我们的要求解析（有些自定义标签必须能够解析）
			// 注册新的结点解析器，其实我觉得在htmlparser的源码里面可以直接编写新的节点类，然后重新编译
			PrototypicalNodeFactory factory = new PrototypicalNodeFactory();
			factory.registerTag(new FontTag());
			parser.setNodeFactory(factory);

			NodeFilter filterAttribute = new HasAttributeFilter(attributeName,
					attributeValue);
			NodeFilter filterTag = new TagNameFilter(tagName);
			NodeFilter andFilter = new AndFilter(filterAttribute, filterTag);

			return parser.parse(andFilter);// 如果没有对应的节点，则会返回size=0的NodeList
		} catch (ParserException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据给定的节点名字提取出符合条件的所有tag节点
	 * 
	 * @param url
	 * @return 符合条件的List
	 */
	public static NodeList getNodeList(String url, String tagName) {
		ConnectionManager manager;
		manager = org.htmlparser.lexer.Page.getConnectionManager();
		Parser parser;
		try {
			parser = new Parser(manager.openConnection(url));
			parser.setEncoding("UTF-8");

			// 下面的节点注册一定要放在最前面，才能把指定节点的所有孩子节点都按我们的要求解析（有些自定义标签必须能够解析）
			// 注册新的结点解析器，其实我觉得在htmlparser的源码里面可以直接编写新的节点类，然后重新编译
			PrototypicalNodeFactory factory = new PrototypicalNodeFactory();
			factory.registerTag(new FontTag());
			parser.setNodeFactory(factory);

			NodeFilter filterTag = new TagNameFilter(tagName);
			return parser.parse(filterTag);// 如果没有对应的节点，则会返回size=0的NodeList
		} catch (ParserException e) {
			e.printStackTrace();
			return null;
		}
	}
}
