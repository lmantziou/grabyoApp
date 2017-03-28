<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1.0,maximum-scale=1.0, minimum-scale=1.0"/>

        <link rel="stylesheet" href="css/my.css" />
        <title>Grab TV Live Tweets</title>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
        <script src="https://code.highcharts.com/stock/highstock.js"></script>
        <script src="https://code.highcharts.com/stock/modules/exporting.js"></script>

        <script src = "js/realTimeDiagram.js" ></script> 
    </head>
    <body>
        <div class="loader" id ="loader">
            <div class="loader-icon"></div>
        </div>
        <header>
            <h1>
                Velocity Twitter Graph
                <span class="hashtag"></span>
            </h1>
        </header>
    
        <div class="container">
            <div id="container-chart" class="container-chart"></div>
        
            <button class="stop-btn">Stop streaming</button>
        </div>
            
        <footer>
            <p>The above Velocity Twitter Graph is part of the project for the studio video editor of the Grabyo platform. </p>
            <p>You can find a detailed documentation in <a href="https://github.com/lmantziou/grabyoApp">Github</a>.</p>
            <p>Eleni Mantziou, 2017</p>
        </footer>

    </body>
</html>
