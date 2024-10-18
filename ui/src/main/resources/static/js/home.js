function handleUserDropdown(event) {
    const value = event.target.value;
    switch (value) {
        case 'profile':
            window.location.href = '/ui/profile';
            break;
        case 'logout':
            window.location.href = '/ui/logout';
            break;
        case 'manage':
            window.location.href = '/ui/users';
            break;
        default:
            console.error(`Invalid dropdown selection: ${value}`);
            break;
    }
    event.target.selectedIndex = 0;
}
