package com.wt.openapi.common.exception;

public class UserHandleableException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * response Error Code
	 */
	private String errorCode = "";
	
	/**
	 * 에러메세지
	 */
	private String errorMessage = "";

    /**
     * 에러코드
     *
     * @param errorCode
     * @param messageKey
     */
    public UserHandleableException(String errorCode, String messageKey) {
    	
    	super("error");
        /*super(WCommon.getMessage(messageKey));
        this.errorMessage = WCommon.getMessage(messageKey);
        this.errorCode = errorCode;*/
    }
    

    /**
     * 에러코드와 파라미터들
     *
     * @param errorCode
     * @param messageKey
     * @param errorParams
     */
    public UserHandleableException(String errorCode, String messageKey, Object[] errorParams) {

        /*super(WCommon.getMessage(messageKey, errorParams));
        this.errorMessage = WCommon.getMessage(messageKey, errorParams);
        this.errorCode = errorCode;*/

    }


    /**
     * 에러코드와 파라미터들, 디폴트 메세지
     *
     * @param errorCode
     * @param messageKey
     * @param errorParams
     * @param defaultMessage
     */
    public UserHandleableException(String errorCode, String messageKey, Object[] errorParams, String defaultMessage) {
    	
        /*super(WCommon.getMessage(messageKey, errorParams, defaultMessage));
        this.errorMessage = WCommon.getMessage(messageKey, errorParams, defaultMessage);
        this.errorCode = errorCode;*/
        
    }        

    /**
     * 에러 메세지를 얻는다.
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

    
}
