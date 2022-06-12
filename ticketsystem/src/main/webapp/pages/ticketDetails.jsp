<h2>Ticket Details</h2>
<h3><a href="${pageContext.request.contextPath}/">Go Home</a></h3>
<form method="post" action="buyTicket">
    <table>
        <tr>
            <th>From Zone </th>
            <td>
                <input type="text" name="fzoneid" value="${fzoneid}" readonly="readonly" />
            </td>
        </tr>
        <tr>
            <th>From Station </th>
            <td>
                <input type="text" name="fstationname" value="${fstationname}" readonly="readonly" />
            </td>
        </tr>
        <tr>
            <th>From Gate </th>
            <td>
                <input type="text" name="fgateno" value="${fgateno}" readonly="readonly" />
            </td>
        </tr>
        <tr>
            <th>From TicketMachine </th>
            <td>
                <input type="text" name="machineid" value="${machineid}" readonly="readonly" />
            </td>
        </tr>
        <tr>
            <th>To Zone </th>
            <td>
                <input type="text" name="tzoneid" value="${tzoneid}" readonly="readonly" />

            </td>
        </tr>
        <tr>
            <th>To Station </th>
            <td>
                <input type="text" name="tstationname" value="${tstationname}" readonly="readonly" />

            </td>
        </tr>
        <tr>
            <th>Ticket Date(yyyy-MM-dd) </th>
            <td>
                <input type="text" name="d" id="d" value="${d}" readonly="true" />
            </td>
        </tr>
        <tr>
            <th>Ticket Time(HH:mm:ss) </th>
            <td>
                <input type="text" name="t" id="t" value="${t}" readonly="true" />
            </td>
        </tr>
        <tr>
            <th>Ticket Type </th>
            <td>
                <input type="text" name="tickettype" value="${tickettype}" readonly="true" />
            </td>
        </tr>
        <tr>
            <th>Zone Count </th>
            <td>
                <input type="text" name="zonecount" value="${zonecount}" readonly="true" />
            </td>
        </tr>
        <tr>
            <th>Ticket Valid Upto </th>
            <td>
                <input type="text" name="validdt" value="${validdt}" readonly="true" />
            </td>
        </tr>
        <tr>
            <th>Ticket Price </th>
            <td>
                <input type="text" name="price" value="${price}" readonly="true" />
            </td>
        </tr>
        <tr>
            <th>Enter card no </th>
            <td>
                <input type="text" name="cardno" value="" />
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Buy Ticket" name="submit" /></td>
            <td><input type="reset" value="reset" name="reset" /></td>
        </tr>
    </table>
</form>
