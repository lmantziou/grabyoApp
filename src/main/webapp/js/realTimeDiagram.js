$(function () {

    var chart;
    //path to call internal services in back-end as configured in ApplicationConfig file.
    var baseUrl = "rest/";
    //hashtags for testing
    var hashtag = "#SurvivorGR";  //"#PremierLeague" "#poundcoin"
    // interval to calculate when the next refresh will be made. 
    // e.g 10 indicates that every 10 secs chart will refresh the data that takes from the back-end
    var interval = 20;
    //this variable is used to determine the tweet id that we need to search data from this and forth.
    var sinceID = 0;
    //used to close time interval
    var streamingTweets;
    $(document).ready(function () {

        $(".hashtag").html(hashtag);


//draw chart with no data
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

//collects data periodically (every 10,20,30...60 secs) and updates the graph real-time
    streamingTweets = setInterval(function () {
        var encodedHashtag = encodeURIComponent(hashtag);
        //call back-end service and get the results in JSON format.

        $.getJSON(baseUrl + "getCountTweets?hashtag=" + encodedHashtag + "&interval=" + interval + "&sinceID=" + sinceID, function (data) {
            if (data !== null) {
                document.getElementById("loader").style.display = "none";
                //update sinceID in request
                sinceID = data.sinceID;
            }
            var shift = chart.series[0].data.length > 20;
            //update data
            var x = (new Date()).getTime(), // current time
                    y = data.count;

            //draw/update the chart
            chart.series[0].addPoint([x, y], true, shift);
        });
    }, interval * 1000);


    chart = Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });


    //stop streaming if button clicked
    $('.stop-btn').click(function () {
        clearInterval(streamingTweets);
    });


});