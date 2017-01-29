/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodigoServlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mario
 */
@WebServlet(name = "Generador", urlPatterns = {"/Generador"})
public class Generador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        
        /*Primero definimos los atributos, correspondientes a los datos que recibimos del
        documento*/
        
        String anchura = request.getParameter("E_Width");
        String altura = request.getParameter("E_Height");
        String maxSize = request.getParameter("E_maxSize");
        String deinterlace = request.getParameter("deinterlace");
        String codecVideo = "\'"+request.getParameter("codecV")+"\'";
        String codecAudio = "\'"+request.getParameter("codecA")+"\'";
        String calidadVideo = request.getParameter("E_videoQuality");
        String videoBitrate = request.getParameter("E_videoBitrate");
        String calidadAudio = request.getParameter("E_calidadAudio");
        String audioBitrate = request.getParameter("E_audioBitrate");
        String twoPass = request.getParameter("E_twopass");
        String sinEscalado = request.getParameter("E_Escalado");
        String sinRuido = request.getParameter("E_denoise");
        String brillo = request.getParameter("E_brightness");
        String contraste = request.getParameter("E_contrast");
        String gamma = request.getParameter("E_gamma");
        String saturacion = request.getParameter("E_saturation");
        String noAudio = request.getParameter("E_noaudio");
        String noVideo = request.getParameter("E_novideo");
        
        /*Hemos establecido una serie de valores por defecto, de manera que 
        siempre vamos a poder realizar la conversion. Claro, un vídeo no 
        puede tener de dimension 0, pero en esos casos el 0 se utiliza para
        establecer un condicional*/
       
        int anchura1 = Integer.parseInt(anchura);
        int altura1 = Integer.parseInt(altura);
        int maxSize1 = Integer.parseInt(maxSize);
        
        int videoBitrate1 = Integer.parseInt(videoBitrate);
        int audioBitrate1 = Integer.parseInt(audioBitrate);
        
        float calidadAudio1 = Float.parseFloat(calidadAudio);
        float brillo1 = Float.parseFloat(brillo);
        float calidadVideo1 = Float.parseFloat(calidadVideo);
        float contraste1 = Float.parseFloat(contraste);
        float gamma1 = Float.parseFloat(gamma);
        float saturacion1 = Float.parseFloat(saturacion);
        
        /*Ahora hay que hacer lo mismo pero con booleanos*/
        
        /*Las etiquetas de los booleanos retornan siempre true, de manera
        que si no se pulsan, tal como está especificado abajo, esquivale a
        un false, porque no se modifica el atributo*/
        
        boolean deinterlace1 = false;
        boolean sinEscalado1 = false;
        boolean twoPass1 = false;
        boolean sinRuido1 = false;
        boolean noAudio1 = false;
        boolean noVideo1 = false;
        
        deinterlace1 = Boolean.parseBoolean(deinterlace);
        sinEscalado1 = Boolean.parseBoolean(sinEscalado);
        twoPass1 = Boolean.parseBoolean(twoPass);
        sinRuido1 = Boolean.parseBoolean(sinRuido);
        noAudio1 = Boolean.parseBoolean(noAudio);
        noVideo1 = Boolean.parseBoolean(noVideo);

        /*Ahora definimos lo que es el String genérico que vamos a insertar*/
        
        String opciones = "var options = JSON.stringify({"
                
                + "'audioBitrate': "+ audioBitrate1 +","
                + "'noUpscaling': "+ sinEscalado1 +","
                + "'deinterlace': "+deinterlace1+","
                + "'videoBitrate': "+videoBitrate1+","
                + "'audioQuality': "+calidadAudio1+","
                + "'brightness': "+brillo1+"," 
                + "'videoQuality': "+calidadVideo1+"," 
                + "'contrast': "+contraste1+","
                + "'gamma': "+gamma1+"," 
                + "'saturation': "+saturacion1+"," 
                + "'twopass': "+ twoPass1 +","
                + "'denoise': "+ sinRuido1+"," 
                + "'noaudio': "+noAudio1+","
                + "'novideo': "+noVideo1+"," 
                + "'videoCodec':" +codecVideo+","
                + "'audioCodec': "+codecAudio
                
        + "});\n";
        
        /*Vamos con la estructura lógica*/
        
        /*La cuestión es el tamaño, maxSize solo se da por válido cuando la 
        anchura y la altura son 0, lo que equivale a que no han sido
        seleccionados. Por lo tanto mando unas opciones en las que solo está
        el maxSize, y no aparecen ni altura ni anchura*/
        
        
        
        if ((altura1==0)&&(anchura1==0)&&(maxSize1!=0)) {
            
             String opciones2 = "var options = JSON.stringify({"
                
                + "'maxSize': "+ maxSize1 +","
                + "'audioBitrate': "+ audioBitrate1 +","
                + "'noUpscaling': "+ sinEscalado1 +","
                + "'deinterlace': "+deinterlace1+","
                + "'videoBitrate': "+videoBitrate1+","
                + "'audioQuality': "+calidadAudio1+","
                + "'brightness': "+brillo1+"," 
                + "'videoQuality': "+calidadVideo1+"," 
                + "'contrast': "+contraste1+","
                + "'gamma': "+gamma1+"," 
                + "'saturation': "+saturacion1+"," 
                + "'twopass': "+ twoPass1 +","
                + "'denoise': "+ sinRuido1+"," 
                + "'noaudio': "+noAudio1+","
                + "'novideo': "+noVideo1+"," 
                + "'videoCodec':" +codecVideo+","
                + "'audioCodec': "+codecAudio
                
        + "});\n";
             
             opciones = opciones2;
             
        }
        
        /*Ahora lo mismo, pero con las otras dos dimensiones. La diferencia, es
        que si selecciona solo una, tiene que recalcular la otra, por lo que solo
        introducimos la seleccionada en opciones*/
        
        if ((altura1!=0)&&(anchura1==0)) {
            
             String opciones2 = "var options = JSON.stringify({"
                
                + "'height': "+ altura1 +","
                + "'audioBitrate': "+ audioBitrate1 +","
                + "'noUpscaling': "+ sinEscalado1 +","
                + "'deinterlace': "+deinterlace1+","
                + "'videoBitrate': "+videoBitrate1+","
                + "'audioQuality': "+calidadAudio1+","
                + "'brightness': "+brillo1+"," 
                + "'videoQuality': "+calidadVideo1+"," 
                + "'contrast': "+contraste1+","
                + "'gamma': "+gamma1+"," 
                + "'saturation': "+saturacion1+"," 
                + "'twopass': "+ twoPass1 +","
                + "'denoise': "+ sinRuido1+"," 
                + "'noaudio': "+noAudio1+","
                + "'novideo': "+noVideo1+"," 
                + "'videoCodec':" +codecVideo+","
                + "'audioCodec': "+codecAudio
                
        + "});\n";
             
             opciones = opciones2;
             
        }
        
        /*Con la anchura*/
        
        if ((altura1==0)&&(anchura1!=0)) {
            
             String opciones2 = "var options = JSON.stringify({"
                
                + "'width': "+ anchura1 +","
                + "'audioBitrate': "+ audioBitrate1 +","
                + "'noUpscaling': "+ sinEscalado1 +","
                + "'deinterlace': "+deinterlace1+","
                + "'videoBitrate': "+videoBitrate1+","
                + "'audioQuality': "+calidadAudio1+","
                + "'brightness': "+brillo1+"," 
                + "'videoQuality': "+calidadVideo1+"," 
                + "'contrast': "+contraste1+","
                + "'gamma': "+gamma1+"," 
                + "'saturation': "+saturacion1+"," 
                + "'twopass': "+ twoPass1 +","
                + "'denoise': "+ sinRuido1+"," 
                + "'noaudio': "+noAudio1+","
                + "'novideo': "+noVideo1+"," 
                + "'videoCodec':" +codecVideo+","
                + "'audioCodec': "+codecAudio
                
        + "});\n";
             
             opciones = opciones2;
             
        }
        
        /*Ahora llega la última opción, en la que especifico ambos valores y
        quiero que se deforme el vídeo*/
        
        if ((altura1!=0)&&(anchura1!=0)) {
            
             String opciones2 = "var options = JSON.stringify({"
                
                + "'height': "+ altura1 +","
                + "'width': "+ anchura1 +","
                + "'audioBitrate': "+ audioBitrate1 +","
                + "'noUpscaling': "+ sinEscalado1 +","
                + "'deinterlace': "+deinterlace1+","
                + "'videoBitrate': "+videoBitrate1+","
                + "'audioQuality': "+calidadAudio1+","
                + "'brightness': "+brillo1+"," 
                + "'videoQuality': "+calidadVideo1+"," 
                + "'contrast': "+contraste1+","
                + "'gamma': "+gamma1+"," 
                + "'saturation': "+saturacion1+"," 
                + "'twopass': "+ twoPass1 +","
                + "'denoise': "+ sinRuido1+"," 
                + "'noaudio': "+noAudio1+","
                + "'novideo': "+noVideo1+"," 
                + "'videoCodec':" +codecVideo+","
                + "'audioCodec': "+codecAudio
                
        + "});\n";
             
             opciones = opciones2;
             
        }
         
        /*En el supuesto de que no se selecciones ningún atributo de dimensiones
         del vídeo, no salta ningún condicional, y tenemos un String opciones que
         no incorpora ninguno de esos atributos, dejando las dimensiones del 
         vídeo tal cual; y siempre el Script va a tener su String opciones*/
        
    
            
        
        try  {
            PrintWriter out = response.getWriter();
            /*El JavaScript no se va a cargar desde ningún archivo, va a ser
            definido y lanzado entre las etiquetas*/
            out.println(""+ 
"<!DOCTYPE html>\n" +
"<html lang = \"en\">\n" +
"\n" +
"	<!La idea es que cuando uno completa el formulario de conversión, s recogen los datos, se forma un JavaScript personalizado con los parámetros adecuados y se coloca al final de esta página; que no es más que el fondo con una barra de cargado. Modifico el método actualizarProgreso del Firefogg para que cuando este termine, reescriba por completo el documento mostrando lo que sería paginaExito, así metemos dos páginas en una.>\n" +
"\n" +
"<head>\n" +
"	<title>Theor</title>\n" +
"	<meta charset=\"UTF-8\">\n" +
"	<meta name= \"viewport\" content = \"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
"	<link href=\"https://fonts.googleapis.com/css?family=Cinzel|Cinzel+Decorative|Cormorant+SC|Orbitron\" rel=\"stylesheet\"> \n" +
"	<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"favicon.jpg\">\n" +
"	<script type=\"text/javascript\" src = \"JQuery.js\"></script>\n" +
"	<style type=\"text/css\">\n" +
"	* {\n" +
"		padding: 0;\n" +
"		margin: 0;\n" +
"		-webkit-box-sizing:border-box;\n" +
"		-moz-box-sizing:border-box;\n" +
"		box-sizing: border-box;\n" +
"	}\n" +
"\n" +
"	body {\n" +
"		font-family: Orbitron;\n" +
"		background:url(fondo.png);\n" +
"		background-size: cover;\n" +
"		background-attachment: fixed;\n" +
"	}	\n" +
"\n" +
"	.contenedor {\n" +
"		width:90%;\n" +
"		max-width:1000px;\n" +
"		margin:auto;\n" +
"	}\n" +
"\n" +
"	#particles-js {\n" +
"		width:100%;\n" +
"		height:100%;\n" +
"		position: fixed;\n" +
"		background:rgba(0,0,0,0.8);\n" +
"	}\n" +
"\n" +
"	.texto {\n" +
"		color:rgb(235,125,16);\n" +
"		margin-right:35%; \n" +
"		margin-left:35%;\n" +
"		height: 55%;\n" +
"		margin-bottom: 7%;\n" +
"		position: relative;\n" +
"		/*Posible solución a los problemas de capas delante-detrás\n" +
"		z-index*/\n" +
"		padding: 200px 0;\n" +
"		text-align: center;\n" +
"		font-weight: 400;\n" +
"	}\n" +
"\n" +
"	.texto h2, .texto p {\n" +
"		background: rgba(0,0,0,0.7);\n" +
"		display: inline-block;\n" +
"		padding:10px 20px;\n" +
"	}\n" +
"\n" +
"	.texto h2 {\n" +
"		font-size: 50px;\n" +
"		letter-spacing: 2.5px;\n" +
"		font-weight: normal;\n" +
"	}\n" +
"\n" +
"	.texto p {\n" +
"		font-size:80%;\n" +
"		letter-spacing:2.5px;\n" +
"	}\n" +
"\n" +
"	.principal {\n" +
"		position: relative;\n" +
"		color:#000;\n" +
"	}\n" +
" \n" +
"	.mensComp {\n" +
"		display: inline-block;\n" +
"		width: 30%;\n" +
"		margin-right: 35%;\n" +
"		margin-left: 35%; \n" +
"		margin-bottom: 2%;\n" +
"		color:rgb(235,125,16);\n" +
"		background-color: rgba(0,0,0,0);\n" +
"		text-align: center;\n" +
"	}\n" +
"\n" +
"	.parpadea {\n" +
"		animation-name: parpadeo;\n" +
"		animation-duration: 1.25s;\n" +
"		animation-timing-function: linear;\n" +
"		animation-iteration-count: infinite;\n" +
"\n" +
"		-webkit-animation-name:parpadeo;\n" +
"		-webkit-animation-duration:1.25s;\n" +
"		-webkit-animation-timing-function:linear;\n" +
"		-webkit-animation-iteration-count: infinite;\n" +
"	}\n" +
"\n" +
"	@-moz-keyframes parpadeo {\n" +
"\n" +
"		0% { opacity: 1.0; }\n" +
"		50% { opacity: 0.0; }\n" +
"		100% { opacity: 1.0; }\n" +
"	}\n" +
"\n" +
"	@-webkit-keyframes parpadeo {\n" +
"\n" +
"		0% { opacity: 1.0; }\n" +
"		50% { opacity: 0.0; }\n" +
"		100% { opacity: 1.0; }\n" +
"	}\n" +
"\n" +
"	@keyframes parpadeo {\n" +
"\n" +
"		0% { opacity: 1.0; }\n" +
"		50% { opacity: 0.0; }\n" +
"		100% { opacity: 1.0; }\n" +
"	}\n" +
"\n" +
"\n" +
"	#btnInv1, #btnInv2{\n" +
"		text-align: center;\n" +
"		position:relative;\n" +
"		width: 80%;\n" +
"		height: 6%;\n" +
"		margin: 5% 10%;\n" +
"		font-weight: bold;\n" +
"		font-family: Orbitron;\n" +
"		font-size: 100%;\n" +
"		color: rgb(235,125,16);\n" +
"		border-color: rgba(0,0,0,0);\n" +
"		background-color: rgba(0,0,0,0);\n" +
"	}\n" +
"\n" +
"	#btnInv1:hover, #btnInv2:hover{\n" +
"		border-radius: 5%;\n" +
"		color: #000;\n" +
"		border-color: rgba(0,0,0,0);\n" +
"		background-color: rgb(235,125,16);\n" +
"\n" +
"	}\n" +
"\n" +
"	.Cbotones {\n" +
"		width:30%;\n" +
"		margin: 0 auto;\n" +
"	}\n" +
"\n" +
"	.botones {\n" +
"		display: inline-block;\n" +
"		position: relative;\n" +
"		height: 5%;\n" +
"		width:80%;\n" +
"		margin: 3% 10%;\n" +
"	}\n" +
"\n" +
"	progress {\n" +
" 		-webkit-appearance: none;\n" +
" 		-moz-appearance: none;\n" +
" 		appearance: none;\n" +
" 	}\n" +
"\n" +
" 	progress {\n" +
" 		border: 0;\n" +
" 		margin: 0 10%;\n" +
" 		width: 80%;\n" +
" 		background-color: rgba(0,0,0,0.5);\n" +
"  	}\n" +
"\n" +
" 	progress::-moz-progress-bar {\n" +
" 		background-color: rgba(235,125,16,0.6);\n" +
" 		-moz-border-radius: 4%;\n" +
" 		-webkit-border-radius: 4%;\n" +
" 	}\n" +
"	</style>\n" +
"</head>\n" +
"\n" +
"<body>\n" +
"\n" +
"	<div id = \"particles-js\"></div>\n" +
"\n" +
"	<div class = \"texto\">\n" +
"		<h2>Theor</h2>\n" +
"		<br>\n" +
"		<p>El otro software de conversión</p>\n" +
"	</div>\n" +
"\n" +
"	<div id = \"BB-8\" class = \"parpadea mensComp\"><p>Codificación Completada</p></div>\n" +
"\n" +
"	<div class = \"contenedor principal\">\n" +
"		<progress id = \"progressbar\" value = \"0\" max = \"10000\"></progress>\n" +
"\n" +
"		<form id = \"formI\" class = \"Cbotones\" action= \"ProyectoTheor\" method = \"POST\">\n" +
"			<input type = \"submit\" name=\"nombreBoton\" value = \"Volver\" id = \"btnInv1\" class = \"botones\">\n" +
"		</form>\n" +
"\n" +
"		<form id = \"formII\" class = \"Cbotones\" action= \"ProyectoTheor\" method = \"POST\">\n" +
"			<input type = \"submit\" name=\"nombreBoton\" value = \"Cerrar Sesion\" id = \"btnInv1\" class = \"botones\">\n" +
"		</form>\n" +
"	</div>\n" +
"	<script type=\"text/javascript\">\n" +
"	(function () {\n" +
"		/*Esto está demostrado que funciona que esconde los contenedores*/\n" +
"		function esconde () {\n" +
"			var mensaje = document.getElementById(\"BB-8\");\n" +
"			mensaje.style.display='none';\n" +
"\n" +
"			var formularioI = document.getElementById(\"formI\");\n" +
"			formularioI.style.display='none';\n" +
"\n" +
"			var formularioII = document.getElementById(\"formII\");\n" +
"			formularioII.style.display='none';\n" +
"		}\n" +
"		esconde();\n" +
"		\n" +
"	}())\n" +
"	</script>\n" +
"	<script type=\"text/javascript\">\n" +
"	if(typeof(Firefogg) == 'undefined') {\n" +
"  	alert('You dont have Firefogg, please go to http://firefogg.org to install it');\n" +
" 	window.open('http://firefogg.org');\n" +
"	}\n" +
"\n" +
"	var ogg = new Firefogg();\n" +
"\n" +
"	if(ogg.selectVideo()) {\n" +
opciones +
"  		ogg.encode(options,\n" +
"            function(result, file) {\n" +
"                result = JSON.parse(result);\n" +
"                update_progress(result.progress, result.state);\n" +
"\n" +
"\n" +
"                var xhr = new XMLHttpRequest();\n" +
"                xhr.addEventListener('progress', function(e) {\n" +
"                    var progress = e.position || e.loaded, total = e.totalSize || e.total;\n" +
"                    update_progress(progress, 'uploading');\n" +
"                }, false);\n" +
"                xhr.open('post', uploadUrl, true);\n" +
"                xhr.send(file)\n" +
"            },\n" +
"            function(progress) {\n" +
"                progress = JSON.parse(progress);\n" +
"                update_progress(progress.progress, progress.state);\n" +
"            }\n" +
"    	);\n" +
"	}\n" +
"\n" +
"\n" +
"	function update_progress(progress, text) {\n" +
"    	var progressbar = document.getElementById('progressbar');\n" +
"    	var relleno = parseInt(progress*10000);\n" +
"    	progressbar.setAttribute(\"value\",relleno);\n" +
"    	if (relleno == 10000) {\n" +
"      	muestra();\n" +
"    	}\n" +
"	}\n" +
"\n" +
"	function muestra () {\n" +
"			var mensaje = document.getElementById(\"BB-8\");\n" +
"			mensaje.style.display='block';\n" +
"\n" +
"			var formularioI = document.getElementById(\"formI\");\n" +
"			formularioI.style.display='block';\n" +
"\n" +
"			var formularioII = document.getElementById(\"formII\");\n" +
"			formularioII.style.display='block';\n" +
"		}\n" +
"	</script>\n" +
"	<script type=\"text/javascript\" src = \"particles.js\"></script>\n" +
"	<script type=\"text/javascript\" src = \"configuracion.js\"></script>\n" +
"\n" +
"</body>\n" +
"</html>");
        } catch (IOException ioe) {
            
        }
    }
    /*Se han emleado conocimientos de W3Schools*/

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
