/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var baseUrl = "rest/";


function globalInit() {
    var hashtag = "#SurvivorGR";

    var encodedHashtag = encodeURIComponent(hashtag);

    $.getJSON(baseUrl + "getCountTweets/gett?hashtag=" + encodedHashtag, function (data) {

        if (data !== null) {
            console.log("data ", JSON.stringify(data));

        }
    });
}
;


//$(function () {
//  
//    
//    
//    
//    Highcharts.setOptions({
//        global: {
//            useUTC: false
//        }
//    });
//
//// Create the chart
//    Highcharts.stockChart('container', {
//        chart: {
//            events: {
//                load: function () {
//
//                    // set up the updating of the chart each second
//                    var series = this.series[0];
//                    setInterval(function () {
//                        var x = (new Date()).getTime(), // current time
//                                y = Math.round(Math.random() * 100);
//                        series.addPoint([x, y], true, true);
//                    }, 1000);
//                }
//            }
//        },
//
//        rangeSelector: {
//            buttons: [{
//                    count: 1,
//                    type: 'minute',
//                    text: '1M'
//                }, {
//                    count: 5,
//                    type: 'minute',
//                    text: '5M'
//                }, {
//                    type: 'all',
//                    text: 'All'
//                }],
//            inputEnabled: false,
//            selected: 0
//        },
//
//        title: {
//            text: 'Live random data'
//        },
//
//        exporting: {
//            enabled: false
//        },
//
//        series: [{
//                name: 'Random data',
//                data: (function () {
//                    // generate an array of random data
//                    var data = [],
//                            time = (new Date()).getTime(),
//                            i;
//
//                    for (i = -999; i <= 0; i += 1) {
//                        data.push([
//                            time + i * 1000,
//                            Math.round(Math.random() * 100)
//                        ]);
//                    }
//                    return data;
//                }())
//            }]
//    });
//
//});