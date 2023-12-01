package com.innovation.center.focusgroup;

public class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
    }

    public static class UsernameAlreadyExistsException extends ApplicationException {
        public UsernameAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class UserNotFoundException extends ApplicationException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    public static class InvalidPasswordException extends ApplicationException {
        public InvalidPasswordException(String message) {
            super(message);
        }
    }

    public static class DuplicateCompanyException extends ApplicationException {

        public DuplicateCompanyException(String message) {
            super(message);
        }
    }

    // Other exception types can be added here

}
