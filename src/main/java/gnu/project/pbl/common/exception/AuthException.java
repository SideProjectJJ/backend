package gnu.project.pbl.common.exception;


import gnu.project.pbl.common.error.ErrorCode;
import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

	private final ErrorCode errorCode;

	public AuthException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

}
