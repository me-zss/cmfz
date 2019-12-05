<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: shun
  Date: 2019/12/1
  Time: 下午2:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<c:set var="path" value="${pageContext.request.contextPath}" scope="page"/>
<script type="text/javascript" src="${path}/incubator-echarts-4.5.0/dist/echarts.js"></script>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="userDiv" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    let goEasy = new GoEasy({
        host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
        appkey: "BS-f10d287315c942d391105f8b7b17c352", //替换为您的应用appkey
    });
    function changeOption(o,c) {
        $.post('${path}/user/findNewUsers',{},function(result) {
            o.series[0].data = result.listMan;
            o.series[1].data = result.listFeman;
            c.setOption(o);
        },'json');
    }
    let chart;
    let op;
    $(function () {
        // 基于准备好的dom，初始化echartss实例
        let myChart = echarts.init(document.getElementById('userDiv'));

        // 指定图表的配置项和数据
        let option = {
            title : {
                text: '持明法洲App用户注册统计',
                subtext:'单位：人'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['男','女']
            },
            toolbox: {
                show : true,
                feature : {
                    dataView : {show: false, readOnly: false},
                    magicType : {show: false, type: ['line', 'bar']},
                    restore : {show: false},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : ['当天注册用户','近一周注册用户','近一月注册用户','近一年注册用户']
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'男',
                    type:'bar',
                    data:[2, 4, 7, 23],
                    label: {
                        normal: {
                            show: true,
                            position: 'inside'
                        }
                    }
                },
                {
                    name:'女',
                    type:'bar',
                    data:[2, 5, 9, 26],
                    label: {
                        normal: {
                            show: true,
                            position: 'inside'
                        }
                    }
                }
            ]
        };
        chart = myChart;
        op = option;
        changeOption(option,myChart);
    });
    goEasy.subscribe({
        channel: "registers", //替换为您自己的channel
        onMessage: function (message) {
            let data = JSON.parse(message.content);
            op.series[0].data = data.listMan;
            op.series[1].data = data.listFeman;
            chart.setOption(op);
        }
    });
</script>