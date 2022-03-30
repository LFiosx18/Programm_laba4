package serialPack;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 2L;
    private String body;

    public Response(){}
    public Response (String body) { this.body = body; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    @Override
    public String toString() {
        return "Response{" +
                "body='" + body + '\'' +
                '}';
    }
}
