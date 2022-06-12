<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Holiday List</h2>
<table border="1" width="80%">
    <thead>
        <tr>
            <th>#</th>
            <th>Date</th>
            <th>Remark</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="holiday" items="${holidays}"  varStatus="loopCounter">
            <tr>
                <td>${loopCounter.count}</td>
                <td>${holiday.d}</td>
                <td>${holiday.remark}</td>
                <td>
                    <form method="post" action="editHoliday">
                        <input type="hidden" name="d" value="${holiday.d}" />
                        <input type="submit" value="edit" name="edit" />
                    </form>
                </td>
                <td>
                    <form method="post" action="delHoliday">
                        <input type="hidden" name="d" value="${holiday.d}" />
                        <input type="submit" value="delete" name="delete" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>