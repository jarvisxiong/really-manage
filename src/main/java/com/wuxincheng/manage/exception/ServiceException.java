package com.wuxincheng.manage.exception;

public class ServiceException extends Exception {

	public static final String GENERAL_EXCEPTION = "9999";  

    public static final String VALIDATION_EXCEPTION = "9998";

    public static final String DB_EXCEPTION = "9997";

    public static final String IO_EXCEPTION = "9996";

    public static final String NETWORK_EXCEPTION = "9995";

    public static final String XMLPARSE_EXCEPTION = "9994";

    public static final String XMLSPEC_EXCEPTION = "9993";

    public static final String VERIFYSIGN_EXCEPTION = "9992";
    
    public static final long serialVersionUID = -6278717648274873136L;

    private String code = null;

    public ServiceException() {
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
	
}
