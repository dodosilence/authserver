package cc.moondust.authserver.controller;

import cc.moondust.authserver.controller.model.PageModel;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * Created by j0 on 2016/7/28.
 */

@Controller
public class AccessTokenController extends AbstractController {


    @Override
    protected PageModel service(String uri, Map<String, String> params, HttpMethod method) {

        return null;
    }
}
