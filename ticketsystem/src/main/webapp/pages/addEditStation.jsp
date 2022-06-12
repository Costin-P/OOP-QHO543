<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
    <c:when test="${action.equals('add')}">
        <h2>Add Station</h2>
        <form method="POST" action="insStation">
            <table>
                <tr>
                    <th>Zone Id</th>
                    <td>
                        <select name="zoneid">
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
                    <td><input type="text" name="stationname" value="" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="submit" /></td>
                    <td><input type="reset" value="reset" /></td>
                </tr>
            </table>
        </form>
    </c:when>
    <c:when test="${action.equals('edit')}">
        <h2>Edit Station</h2>
        <form method="POST" action="updStation">
            <input type="hidden" name="id" value="${station.id}" />
            <table>
                <tr>
                    <th>Zone Id</th>
                    <td>
                        <select name="zoneid">
                            <c:forEach var="zone" items="${zones}">
                                <c:choose>
                                    <c:when test="${station.zoneid==zone.id}">
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
                    <th>Station Name</th>
                    <td><input type="text" name="stationname" value="${station.stationname}" /></td>
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
