package cc.moondust.authserver.exception;

/**
 * Created by j0 on 2016/7/28.
 */
public class ResourceNotFoundException extends Exception {
    private String html;

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
