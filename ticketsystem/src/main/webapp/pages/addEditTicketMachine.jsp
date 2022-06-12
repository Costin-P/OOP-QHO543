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
<c:choose>
    <c:when test="${action.equals('add')}">
        <h2>Add Ticket Machine</h2>
        <form method="POST" action="insTicketMachine">
            <table>
                <tr>
                    <th>Machine Id</th>
                    <td>
                        <input type="text" name="machineid" value="" />
                    </td>
                </tr>
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
                    <th>Stationname</th>
                    <td>
                        <select name="stationname" id="stationname">
                            <option>select stationname</option>
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
                    <td><input type="submit" value="submit" /></td>
                    <td><input type="reset" value="reset" /></td>
                </tr>
            </table>
        </form>
    </c:when>
    <c:when test="${action.equals('edit')}">
        <h2>Edit Ticket Machine</h2>
        <form method="POST" action="updTicketMachine">
            <input type="hidden" name="id" value="${ticketMachine.id}" />
            <table>
                <tr>
                    <th>Machine Id</th>
                    <td>
                        <input type="text" name="machineid" value="${ticketMachine.machineid}" />
                    </td>
                </tr>
                <tr>
                    <th>Zone Id</th>
                    <td>
                        <select name="zoneid" id="zoneid">
                            <option>Select ZoneId</option>
                            <c:forEach var="zone" items="${zones}">
                                <c:choose>
                                    <c:when test="${ticketMachine.zoneid==zone.id}">
                                        <option value="${zone.id}" selected="true">
                                            ${zone.id}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${zone.id}">
                                            ${zone.id}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Stationname</th>
                    <td>
                        <select name="stationname" id="stationname">
                            <option>select stationname</option>
                            <c:forEach var="station" items="${stations}">
                                <c:choose>
                                    <c:when test="${ticketMachine.stationname==station.stationname}">
                                        <option value="${station.stationname}" selected="true">
                                            ${station.stationname}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${station.stationname}">
                                            ${station.stationname}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Gate No</th>
                    <td>
                        <select name="gateno" id="gateno">
                            <option>Select GateNo</option>
                            <c:forEach var="gate" items="${gates}">
                                <c:choose>
                                    <c:when test="${gate.gateno==ticketMachine.gateno}">
                                        <option value="${gate.gateno}" selected="true">
                                            ${gate.gateno}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${gate.gateno}">
                                            ${gate.gateno}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="submit" />
                    </td>
                    <td><input type="reset" value="reset" /></td>
                </tr>
            </table>
        </form>
    </c:when>
</c:choose>