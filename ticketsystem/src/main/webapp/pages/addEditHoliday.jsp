<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
    <c:when test="${action.equals('add')}">
        <h2>Add Holiday Details</h2>
        <form method="POST" action="insHoliday">
            <table>
                <tr>
                    <th>Holiday Date (yyyy-MM-dd)</th>
                    <td>
                        <input type="text" name="d" value="" />
                    </td>
                </tr>
                <tr>
                    <th>Remark</th>
                    <td><input type="text" name="remark" value="" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="submit" /></td>
                    <td><input type="reset" value="reset" /></td>
                </tr>
            </table>
        </form>
    </c:when>
    <c:when test="${action.equals('edit')}">
        <h2>Edit Holiday</h2>
        <form method="POST" action="updHoliday">
            <table>
                <tr>
                    <th>Holiday Date (yyyy-MM-dd)</th>
                    <td>
                        <input type="text" name="d" value="${holiday.d}" readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <th>Remark</th>
                    <td><input type="text" name="remark" value="${holiday.remark}" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="submit" /></td>
                    <td><input type="reset" value="reset" /></td>
                </tr>
            </table>
        </form>
    </c:when>
</c:choose>