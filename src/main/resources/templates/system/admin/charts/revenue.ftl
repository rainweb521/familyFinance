
<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <h3 class="box-title">近三月收支信息</h3>

                <div class="box-tools pull-left">
                </div>
            </div>
            <div class="box-body">
                <div class="clearfix">

                </div>
                <table id="security_tab5" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                    <tr>
                        <th>月份</th>
                        <th>支出统计</th>
                        <th>收入统计</th>
                    </tr>
                    </tr>
                    </thead>
                    <tbody>
                    <tr role="row" class="odd">
                        <td  id="data1">${map.month1}</td>
                        <td id="data2">${map.data1_1}</td>
                        <td id="data3">${map.data1_2}</td>
                    </tr>
                    <tr role="row" class="odd">
                        <td id="data4">${map.month2}</td>
                        <td id="data5">${map.data2_1}</td>
                        <td id="data6">${map.data2_2}</td>
                    </tr>
                    <tr role="row" class="odd">
                        <td id="data7">${map.month3}</td>
                        <td id="data8">${map.data3_1}</td>
                        <td id="data9">${map.data3_2}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="box">
            <div id="container" style="height: 100%"></div>
        </div>
    </div>
</div>


<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-gl/dist/echarts-gl.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-stat/dist/ecStat.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/dataTool.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/map/js/china.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/map/js/world.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/bmap.min.js"></script>
<script type="text/javascript">
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        title: {
            text: '',
            subtext: '',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: [document.getElementById("data1").innerText+'支出', document.getElementById("data1").innerText+'收入'
                ,document.getElementById("data4").innerText+'支出',document.getElementById("data4").innerText+'收入'
                ,document.getElementById("data7").innerText+'支出',document.getElementById("data7").innerText+'收入'
               ]
        },
        series: [
            {
                name: '收支统计',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [
                    {value: document.getElementById("data2").innerText, name: document.getElementById("data1").innerText+'支出'},
                    {value: document.getElementById("data3").innerText, name: document.getElementById("data1").innerText+'收入'},
                    {value: document.getElementById("data5").innerText, name: document.getElementById("data4").innerText+'支出'},
                    {value: document.getElementById("data6").innerText, name: document.getElementById("data4").innerText+'收入'},
                    {value: document.getElementById("data8").innerText, name: document.getElementById("data7").innerText+'支出'},
                    {value: document.getElementById("data9").innerText, name: document.getElementById("data7").innerText+'收入'}
                ],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    ;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
</script>