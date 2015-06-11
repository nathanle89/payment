var fs = require('fs');
var path = 'gas_price_output.csv';
var system = require('system');


var page = require('webpage').create();

page.onConsoleMessage = function(msg) {
    system.stderr.writeLine(msg);
};

page.open('http://www.gasbuddy.com', function() {
    page.injectJs("jquery-1.11.3.min.js");

    page.evaluate(function() {
        $("#search-text").val("94401");
        $("#search-btn").click();
    });

    setTimeout(function() {
        var resultString = page.evaluate(function() {
            var gasStations = $('#prices-table-cheapest').children('tr');
            var result = '';
            for (var i = 0; i < gasStations.length; i++) {
                var $gasData = $(gasStations[i]).children();
                var price = $($gasData[0]).find('.gb-price')[0].innerText;
                var gasStationName = $($($gasData[1]).children()[0]).find('a strong')[0].innerText;
                var address = $($gasData[1]).children()[1].innerText;
                var city = $gasData[2].innerText.trim();

                result = result + (price + ';' + gasStationName + ';' + address + ';' + city) + ':';
            }

            return result;
        });

        if (resultString.length > 0) {
            var results = resultString.split(':');
            for (var i = 0; i < results.length; i++) {
                fs.write(path, results[i] + '\n', 'a');
            }
        }

        phantom.exit()
    }, 1000)
});

