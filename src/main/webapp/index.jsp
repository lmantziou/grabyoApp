
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1.0,maximum-scale=1.0, minimum-scale=1.0"/>

        <link rel="stylesheet" href="css/my.css" />
        <title>Grab Live Tweets</title>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
        <script src="https://code.highcharts.com/stock/highstock.js"></script>
        <script src="https://code.highcharts.com/stock/modules/exporting.js"></script>

        <script src = "js/realTimeDiagram.js" ></script> 
    </head>
    <body>
        <div id="container" style="height: 400px; min-width: 310px"></div>


        <div>
            <label for="commentLabel">Add your hashtags:</label>
            <input type="text" name="hash" id="hash" value="">
            
             <a id="stopInterval" href="#"class="" >Stop streaming</a> 
                

        </div>

        <!--  <script type="text/javascript">
              /****************** Global js vars ************************/
  
  
              /* Just call the initialization function when the page loads */
              $(window).load(function () {
                  globalInit();
  
              });
  
          </script>-->

    </body>
</html>
