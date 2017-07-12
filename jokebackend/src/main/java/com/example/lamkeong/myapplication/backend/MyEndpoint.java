/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.lamkeong.myapplication.backend;

import com.example.JavaJoke;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.lamkeong.example.com",
                ownerName = "backend.myapplication.lamkeong.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }
    @ApiMethod(name = "getJoke")
    public MyBean getJoke() {
        MyBean jokeBean = new MyBean();
        JavaJoke javaJoke = new JavaJoke();
        jokeBean.setData(javaJoke.getAnotherJoke());

        return jokeBean;
    }

}
