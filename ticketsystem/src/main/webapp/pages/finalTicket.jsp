<h2>Final Ticket</h2>
<h3><a href="${pageContext.request.contextPath}/bookTicket">Book New Ticket</a></h3>
<table>
    <tr>
        <th>Ticket Id </th>
        <td>${ticket.id}</td>
    </tr>
    <tr>
        <th>From Zone </th>
        <td>${ticket.fzoneid}</td>
    </tr>
    <tr>
        <th>From Gate </th>
        <td>${ticket.fgateno}</td>
    </tr>
    <tr>
        <th>From TicketMachine </th>
        <td>${ticket.machineid}</td>
    </tr>
    <tr>
        <th>To Zone</th>
        <td>${ticket.tzoneid}</td>
    </tr>
    <tr>
        <th>Ticket ValidForm </th>
        <td>${ticket.dt}</td>
    </tr>
    <tr>
        <th>Ticket Type </th>
        <td>${ticket.tickettype}</td>
    </tr>
    <tr>
        <th>Zone Count </th>
        <td>${ticket.zonecount}</td>
    </tr>
    <tr>
        <th>Ticket Valid Upto </th>
        <td>${ticket.validdt}</td>
    </tr>
    <tr>
        <th>Ticket Price </th>
        <td>${ticket.price}</td>
    </tr>
</table>