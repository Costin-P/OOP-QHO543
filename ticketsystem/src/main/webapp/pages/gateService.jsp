<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
    $(function () {
        $("#zoneid").on("change", function () {
            //alert(this.value);
            $.post("${pageContext.request.contextPath}/getStationByZone",
                    {
                        zoneid: this.value
                    },
                    function (data) {
                        //alert(data);
                        $("#stationname").html(data);
                    });
        });
        $("#stationname").on("change", function () {
            //alert(this.value);
            $.post("${pageContext.request.contextPath}/getGateByStation",
                    {
                        zoneid: $("#zoneid").val(),
                        stationname: this.value
                    },
                    function (data) {
                        //alert(data);
                        $("#gateno").html(data);
                    });
        });
    });
</script>
<h2>Gate Service</h2>
<h3>Last Ticket Valid = <%= request.getParameter("message")%></h3>
<form method="post" action="checkTicketValidity">
    <table>
        <tr>
            <th>Zone Id</th>
            <td>
                <select name="zoneid" id="zoneid">
                    <option>Select ZoneId</option>
                    <c:forEach var="zone" items="${zones}">
                        <option value="${zone.id}">
                            ${zone.id}
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>Station Name</th>
            <td>
                <select name="stationname" id="stationname">
                    <option value="">Select StationName</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>Gate No</th>
            <td>
                <select name="gateno" id="gateno">
                    <option>Select GateNo</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>Enter ticketId</th>
            <td><input type="text" name="id" value="" /></td>
        </tr>
        <tr>
            <th>Select Movement Type</th>
            <td>
                <select name="movement">
                    <option value="entry">entry</option>
                    <option value="exit">exit</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>Movement Date(yyyy-MM-dd) </th>
            <td>
                <input type="text" name="md" id="md" value="" />
            </td>
        </tr>
        <tr>
            <th>Movement Time(HH:mm:ss) </th>
            <td>
                <input type="text" name="mt" id="mt" value="" />
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="submit" /></td>
            <td><input type="reset" value="reset" /></td>
        </tr>
    </table>
</form>