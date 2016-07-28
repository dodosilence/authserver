package cc.moondust.authserver.controller;

import cc.moondust.authserver.controller.model.PageModel;
import cc.moondust.authserver.exception.ParamsNotLegalException;
import cc.moondust.authserver.service.AuthClientService;
import cc.moondust.authserver.util.StringUtil;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * Created by j0 on 2016/7/28.
 */
@Controller
public class AuthorizeController extends AbstractController {
    private final String AUTH_TYPE_PASSWORD = "password";
    private final String AUTH_TYPE_AUTHCODE = "authoried_code";

    @Autowired
    AuthClientService authClientService;


    @Override
    protected PageModel service(String uri, Map<String, String> params, HttpMethod method, HttpHeaders headers) throws ParamsNotLegalException {
        String client_id = params.get("client_id");
        String redirect_url = params.get("redirect_url");
        String scope = params.get("scope");
        String auth_type = params.get("auth_type");
        String client_secret = params.get("client_secret");
        String username = params.get("username");
        String password = params.get("password");
        if (StringUtil.hasEmptyStr(client_id, scope, auth_type)) {
            throw new ParamsNotLegalException();
        }
        switch (auth_type) {
            case AUTH_TYPE_PASSWORD:
                if (StringUtil.hasEmptyStr(client_secret, username, password)) {
                    throw new ParamsNotLegalException();
                }else {//参数合法

                    authClientService.authorizedByPassword( );

                }
                break;

            case AUTH_TYPE_AUTHCODE:

                break;
            default:
                break;
        }
        return null;
    }
}
