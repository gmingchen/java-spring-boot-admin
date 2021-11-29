package com.slipper.modules.captcha;

import com.slipper.service.modules.captcha.service.CaptchaService;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@RestController
@RequestMapping("/backstage")
public class CaptchaController {

    @Resource
    private CaptchaService captchaService;

    /**
     * 验证码
     *
     * @api {GET} /backstage/captcha.jpg captcha
     * @apiDescription 验证码图片
     * @apiVersion 1.0.0
     * @apiGroup Captcha
     * @apiName get
     * @apiParamExample 请求参数示例
     * {
     *     uuid: 1
     * }
     */
    @GetMapping("/captcha.jpg")
    public void get(HttpServletResponse response, @RequestParam String uuid) throws IOException {
        ServletOutputStream servletOutputStream = response.getOutputStream();

        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        ImageIO.write(captchaService.createCaptcha(uuid), "jpg", servletOutputStream);
        IOUtils.close(servletOutputStream);
    }


}
