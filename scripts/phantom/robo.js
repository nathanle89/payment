var page = require('webpage').create();
page.open('http://www.amsoil.com/mygarage/vehiclelookuppage.aspx', function() {
    page.includeJs("https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js", function() {
        var result = page.evaluate(function() {
            $("#ctl00_bph1_wucGarageSelector1_txtYear").val("2012");
            $("#ctl00$bph1$wucGarageSelector1$btnBuild").click();
            return $("#ctl00_bph1_wucGarageSelector1_tvVehicles").children("table").length;
        });

        console.log(result);
        phantom.exit()
    });
});