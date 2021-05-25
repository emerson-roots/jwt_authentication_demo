package demo.exceptions;

import java.io.Serializable;

public class StandardErrorPersonalizado implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer statusHttp;
	private String msg; 
	private Long timeStampError;

	public StandardErrorPersonalizado(Integer status, String msg, Long timeStamp) {
		super();
		this.statusHttp = status;
		this.msg = msg;
		this.timeStampError = timeStamp;
	}

	public Integer getStatusHttp() {
		return statusHttp;
	}

	public void setStatusHttp(Integer statusHttp) {
		this.statusHttp = statusHttp;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimeStampError() {
		return timeStampError;
	}

	public void setTimeStampError(Long timeStampError) {
		this.timeStampError = timeStampError;
	}


}
