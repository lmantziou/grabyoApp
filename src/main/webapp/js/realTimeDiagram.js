var baseUrl = "rest/";
var hashtag = "#SurvivorGR";
var test = true;
$(document).ready(function () {
    
    $(".hashtag").html(hashtag);

    $('#stopInterval').click(function () {
        console.log("stop interval");
        clearInterval(streamingTweets);
    });


    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });

    Highcharts.chart('container', {
        chart: {
            type: 'spline',
            animation: Highcharts.svg, // don't animate in old IE
            marginRight: 10,
           
            events: {
                load: function () {
                    // set up the updating of the chart each second
                    var series = this.series[0];
                    
                                       
                    streamingTweets = setInterval(function () {
                        console.log("interval called");
                        var encodedHashtag = encodeURIComponent(hashtag);

                        $.getJSON(baseUrl + "getCountTweets?hashtag=" + encodedHashtag, function (data) {

                            if (data !== null) {
                                  document.getElementById("loader").style.display = "none";
                                console.log("data ", JSON.stringify(data));

                            }
                        var x = (new Date()).getTime(), // current time
                                    y = data;
                               
                        series.addPoint([x, y], true, true);

                        });

                    }, 60000);
                }
            }
        },
           title: {
            text: ''
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 10
        },
        yAxis: {
            title: {
                text: 'Value'
            },
             tickPixelInterval: 35,
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
                data: (function () {
                    // generate an array of random data
                    var data = [],
                            time = (new Date()).getTime(),
                            i;

                    for (i = -2; i <= 0; i += 1) {
                        data.push({
                            x: time + i * 1000,
                            y: 0
                        });
                    }
                    return data;
                }())
            }]
    });


}); //end $(document).ready