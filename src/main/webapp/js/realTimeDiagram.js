$(function () {
    var chart;
    var baseUrl = "rest/";
    var hashtag = "#SurvivorGR";
    var interval = 10;
     var streamingTweets;
    $(document).ready(function () {
         $(".hashtag").html(hashtag);
         
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container-chart',
                type: 'spline',
                animation: Highcharts.svg, // don't animate in old IE
                marginRight: 10
            },
          
            title: {
                text: ''
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150,
                maxZoom: 20 * 1000
            },
            yAxis: {
                title: {
                    text: 'num tweets'
                },
                plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
                            Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                            Highcharts.numberFormat(this.y, 2);
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                    name: 'Streaming data from Twitter',
                    data: []
                }]
        });
    });


     streamingTweets = setInterval(function () {
        console.log("interval called");
        var encodedHashtag = encodeURIComponent(hashtag);
        $.getJSON(baseUrl + "getCountTweets?hashtag=" + encodedHashtag+"&interval="+interval, function (data) {
//
            if (data !== null) {
                document.getElementById("loader").style.display = "none";
                console.log("data ", JSON.stringify(data));
            }
            var shift = chart.series[0].data.length > 20;
            var x = (new Date()).getTime(), // current time
                    y = data;
//                         y = Math.random();
            chart.series[0].addPoint([x, y], true, shift);
        });
    }, interval*1000);


    chart = Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });

    $('.stop-btn').click(function () {
        console.log("stop interval");
        clearInterval(streamingTweets);
    });


});