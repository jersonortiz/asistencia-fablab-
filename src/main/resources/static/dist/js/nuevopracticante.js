let url = serverurl;
//let usuario = user;
let profesional = "";

$(document).ready(function () {
    $("#publiform").submit(function (e) {
        e.preventDefault();

        registrar();
    });
cargarpersonas();
});

function registrar() {
    let loadurl = url + 'practicante';

    let car = $("#idCarnet").val();
    let sem = $("#semestre").val();
    let est = $("#estado").val();
    let per = $("#idPersona").val();

    let data = {
        idCarnet: car,
        semestre: sem,
        estado: est,
        idPersona: {id: per}
  
    };

    console.log(data);
    let init = makeinit(data)

    fetch(loadurl, init)
            .then((resp) => resp.json())
            .then(function (data) {
                if (data.msg) {
                    console.log(data);
                    return;
                }
                console.log(data);

                console.log(data);
                alert("se ha registrado el practicante");
                location.href = "./practicante.html";

            });

}

function cargarpersonas(){

    $('#idPersona').empty();

       let loadurl = url + 'persona';

                let init = makeinitnodat();

                fetch(loadurl, init)
                        .then((resp) => resp.json())
                        .then(function (data) {
                            //curso = data;

                           console.log(data);

                            let fill = ''

                            $.each(data, function (i, item) {
                                fill += '<option value="'+ item.id +'">'+
                                item.nombre + ' ' + item.apellido+' '+ 
                                item.codigo +'</option>';
                            });

                             $('#idPersona').append(fill);

                        });         
 }


function makeinit(data) {
    let heads = new Headers();
    heads.append("Accept", "application/json");
    heads.append("Content-Type", "application/json");
    //heads.append("Authorization", authToken);
    heads.append("Access-Control-Allow-Origin", '*');
    let init = {
        method: 'POST',
        mode: 'cors',
        body: JSON.stringify(data),
        headers: heads
    };
    return init;
}

function makeinitnodat() {
    let heads = new Headers();
    heads.append("Accept", "application/json");
    heads.append("Content-Type", "application/json");
    //heads.append("Authorization", authToken);
    heads.append("Access-Control-Allow-Origin", '*');
    let init = {
        method: 'GET',
        mode: 'cors',
        headers: heads
    };
    return init;
}


"org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot deserialize value of type `int` from Object value (token `JsonToken.START_OBJECT`)
    at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.readJavaType(AbstractJackson2HttpMessageConverter.java:406)
    at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.read(AbstractJackson2HttpMessageConverter.java:354)
    at org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver.readWithMessageConverters(AbstractMessageConverterMethodArgumentResolver.java:184)
    at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.readWithMessageConverters(RequestResponseBodyMethodProcessor.java:161)
    at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.resolveArgument(RequestResponseBodyMethodProcessor.java:135)
    at org.springframework.web.method.support.HandlerMethodArgumentResolverComposite.resolveArgument(HandlerMethodArgumentResolverComposite.java:122)
    at org.springframework.web.method.support.InvocableHandlerMethod.getMethodArgumentValues(InvocableHandlerMethod.java:224)
    at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:178)
    at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:118)
    at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:926)
    at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:831)
    at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
    at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1089)
    at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:979)
    at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014)
    at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:914)
    at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
    at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885)
    at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:195)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
    at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
    at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)
    at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)
    at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
    at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
    at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:482)
    at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:115)
    at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
    at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
    at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:344)
    at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:389)
    at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
    at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:896)
    at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1741)
    at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1190)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
    at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63)
    at java.base/java.lang.Thread.run(Thread.java:1583)
Caused by: com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot deserialize value of type `int` from Object value (token `JsonToken.START_OBJECT`)
 at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 1, column: 113] (through reference chain: com.fablab.ufps.edu.co.asistencia.dto.IngresoSalidaDTO["idPracticante"])
    at com.fasterxml.jackson.databind.exc.MismatchedInputException.from(MismatchedInputException.java:59)
    at com.fasterxml.jackson.databind.DeserializationContext.reportInputMismatch(DeserializationContext.java:1767)
    at com.fasterxml.jackson.databind.DeserializationContext.handleUnexpectedToken(DeserializationContext.java:1541)
    at com.fasterxml.jackson.databind.DeserializationContext.handleUnexpectedToken(DeserializationContext.java:1446)
    at com.fasterxml.jackson.databind.DeserializationContext.extractScalarFromObject(DeserializationContext.java:958)
    at com.fasterxml.jackson.databind.deser.std.StdDeserializer._parseIntPrimitive(StdDeserializer.java:722)
    at com.fasterxml.jackson.databind.deser.std.NumberDeserializers$IntegerDeserializer.deserialize(NumberDeserializers.java:529)
    at com.fasterxml.jackson.databind.deser.std.NumberDeserializers$IntegerDeserializer.deserialize(NumberDeserializers.java:506)
    at com.fasterxml.jackson.databind.deser.impl.MethodProperty.deserializeAndSet(MethodProperty.java:129)
    at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:393)
    at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
    at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:342)
    at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2125)
    at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1501)
    at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.readJavaType(AbstractJackson2HttpMessageConverter.java:395)
    ... 43 more
"