package ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers;

public abstract class LoginObserver extends BusObserver<LoginObserver.LoginButtonClicked> {
    public LoginObserver() { super(LoginButtonClicked.class); }

    public static class LoginButtonClicked {

        private String user;
        private String password;

        public LoginButtonClicked(String user, String password){
            this.user = user;
            this.password = password;
        }

        public String getUser() {
            return user;
        }

        public String getPassword() {
            return password;
        }
    }
}
