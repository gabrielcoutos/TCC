$(document).ready(function () {
    var ctx = document.getElementById('myChart').getContext('2d');
    var chart = new Chart(ctx, {
        // The type of chart we want to create
        type: 'pie',
        // The data for our dataset
        data: {
            labels: ["Aprovados", "Excluídos", "Pendentes"],
            datasets: [{
                    backgroundColor: [
                        'rgba(92, 184, 92,1)',
                        'rgba(217, 83, 79,1)',
                        'rgba(240, 173, 78,1)'
                    ],
                    data: [$("#aprovados").text(), $("#deletados").text(), $("#naoAnalisados").text()]
                }]
        },
        // Configuration options go here
        options: {}
    });
    var ctx1 = document.getElementById("chartPlanos").getContext("2d");
    new Chart(ctx1, {
        // The type of chart we want to create
        type: 'polarArea',
        // The data for our dataset
        data: {
            labels: [$("#planoNome1").text(), $("#planoNome2").text(), $("#planoNome3").text(), $("#planoNome4").text()],
            datasets: [{
                    backgroundColor: [
                        'rgba(56, 135, 167,0.9)',
                        'rgba(185,102, 167,0.9)',
                        'rgba(101, 249, 229,0.9)',
                        'rgba(197, 212, 176,0.9)'
                    ],
                    data: [$("#planoqtd1").text(), $("#planoqtd2").text(), $("#planoqtd3").text(), $("#planoqtd4").text()]
                }]
        },
        // Configuration options go here
        options: {}
    });
    
    var ctx2 = document.getElementById("chartSites").getContext("2d");
    new Chart(ctx2, {
        // The type of chart we want to create
        type: 'doughnut',
        // The data for our dataset
        data: {
            labels: [$("#siteNome1").text(), $("#siteNome2").text(), $("#siteNome3").text(), $("#siteNome4").text(),$("#siteNome5").text()],
            datasets: [{
                    backgroundColor: [
                        'rgba(56, 135, 167,0.9)',
                        'rgba(185,102, 167,0.9)',
                        'rgba(101, 249, 229,0.9)',
                        'rgba(197, 212, 176,0.9)'
                    ],
                    data: [$("#siteqtd1").text(), $("#siteqtd2").text(), $("#siteqtd3").text(), $("#siteqtd4").text(),$("#siteqtd5").text()]
                }]
        },
        // Configuration options go here
        options: {}
    });
    
    var ctx3 = document.getElementById("chartuDias").getContext("2d");
    new Chart(ctx3, {
        // The type of chart we want to create
        type: 'line',
        // The data for our dataset
        data: {
            labels: [$("#uDias5").text(), $("#uDias4").text(), $("#uDias3").text(), $("#uDias2").text(),$("#uDias1").text()],
            datasets: [{
                    label:"Usuários",
                    borderColor: "rgba(0, 54, 180,0.9)",
                    backgroundColor:"rgba(0, 54, 180,0.5)",                                       
                    pointStrokeColor:"rgba(0, 54, 180,0.9)",                                     
                    data: [$("#uDiasqtd5").text(), $("#uDiasqtd4").text(), $("#uDiasqtd3").text(), $("#uDiasqtd2").text(),$("#uDiasqtd1").text()]
                },
                {
                    label:"Consumo",
                    borderColor: "rgba(116, 223, 207,0.9)",
                    backgroundColor:"rgba(116, 223, 207,0.5)",                                       
                    pointStrokeColor:"rgba(116, 223, 207,0.9)",                                     
                    data: [$("#megaqtd5").text(), $("#megaqtd4").text(), $("#megaqtd3").text(), $("#megaqtd2").text(),$("#megaqtd1").text()]
                }]
        },
        // Configuration options go here
        options: {}
    });

    $(function () {
        var data, options;

        // real-time pie chart
        var sysLoad = $('#system-load').easyPieChart({
            size: 130,
            barColor: function (percent) {
                return "rgb(" + Math.round(200 * percent / 100) + ", " + Math.round(200 * (1.1 - percent / 100)) + ", 0)";
            },
            trackColor: 'rgba(245, 245, 245, 0.8)',
            scaleColor: false,
            lineWidth: 5,
            lineCap: "square",
            animate: 800
        });

        var updateInterval = 3000; // in milliseconds

        setInterval(function () {
            var randomVal;
            randomVal = getRandomInt(0, 100);

            sysLoad.data('easyPieChart').update(randomVal);
            sysLoad.find('.percent').text(randomVal);
        }, updateInterval);

        function getRandomInt(min, max) {
            return Math.floor(Math.random() * (max - min + 1)) + min;
        }

    });
});



