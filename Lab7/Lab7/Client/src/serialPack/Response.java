package serialPack;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 2L;
    private String body;
    private boolean isRegistered;
    private boolean isAuthorized;

    public Response(){}
    public Response(String body) { this.body = body; }

    public String getBody() { return body; }
    public boolean isRegistered() { return isRegistered; }
    public boolean isAuthorized() { return isAuthorized; }

    public void setBody(String body) { this.body = body; }
    public void setRegistered(boolean registered) { isRegistered = registered; }
    public void setAuthorized(boolean authorized) {isAuthorized = authorized; }

    @Override
    public String toString() {
        return "Response{" +
                "body='" + body + '\'' +
                ", isRegistered=" + isRegistered +
                ", isAuthorized=" + isAuthorized +
                '}';
    }
}
