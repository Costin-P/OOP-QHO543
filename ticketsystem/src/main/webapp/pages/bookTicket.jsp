<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
    $(function () {
        $("#fzoneid").on("change", function () {
            //alert(this.value);
            $.post("${pageContext.request.contextPath}/getStationByZone",
                    {
                        zoneid: this.value
                    },
                    function (data) {
                        //alert(data);
                        $("#fstationname").html(data);
                    });
        });
        $("#fstationname").on("change", function () {
            //alert(this.value);
            $.post("${pageContext.request.contextPath}/getGateByStation",
                    {
                        zoneid: $("#fzoneid").val(),
                        stationname: this.value
                    },
                    function (data) {
                        //alert(data);
                        $("#fgateno").html(data);
                    });
        });
        $("#fgateno").on("change", function () {
            //alert(this.value);
            $.post("${pageContext.request.contextPath}/getTicketMachineByGate",
                    {
                        zoneid: $("#fzoneid").val(),
                        stationname: $("#fstationname").val(),
                        gateno: this.value
                    },
                    function (data) {
                        //alert(data);
                        $("#machineid").html(data);
                    });
        });
        $("#tzoneid").on("change", function () {
            //alert(this.value);
            $.post("${pageContext.request.contextPath}/getStationByZone",
                    {
                        zoneid: this.value
                    },
                    function (data) {
                        //alert(data);
                        $("#tstationname").html(data);
                    });
        });
    });
</script>
<h2>Book Ticket</h2>
<form method="post" action="getTicketDetails">
    <table>
        <tr>
            <th>From Zone </th>
            <td>
                <select name="fzoneid" id="fzoneid">
                    <option>Select zone</option>
                    <c:forEach var="zone" items="${zones}">
                        <option value="${zone.id}">
                            ${zone.id}
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>From Station </th>
            <td>
                <select name="fstationname" id="fstationname">
                    <option>Select station</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>From Gateno </th>
            <td>
                <select name="fgateno" id="fgateno">
                    <option>Select GateNo</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>From TicketMachine </th>
            <td>
                <select name="machineid" id="machineid">
                    <option>Select TicketMachine</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>To Zone </th>
            <td>
                <select name="tzoneid" id="tzoneid">
                    <option>Select zone</option>
                    <c:forEach var="zone" items="${zones}">
                        <option value="${zone.id}">
                            ${zone.id}
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>To Station </th>
            <td>
                <select name="tstationname" id="tstationname">
                    <option>Select station</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>Ticket Date(yyyy-MM-dd) </th>
            <td>
                <input type="text" name="d" id="d" value="" />
            </td>
        </tr>
        <tr>
            <th>Ticket Time(HH:mm:ss) </th>
            <td>
                <input type="text" name="t" id="t" value="" />
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Get Ticket Details" name="submit" /></td>
            <td><input type="reset" value="reset" name="reset" /></td>
        </tr>
    </table>
</form>
