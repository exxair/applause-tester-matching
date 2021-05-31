document.addEventListener('DOMContentLoaded', function () {
    const params = (new URL(document.location)).searchParams;
    parseQueryParams(params.get('countries'), '*[id^="country"]', document.getElementById('allCountries'));
    parseQueryParams(params.get('devices'), '*[id^="device"]', document.getElementById('allDevices'));
});

function search() {
    const countries = [...document.querySelectorAll('*[id^="country"]')].filter(c => c.checked).map(c => c.value).join();
    const devices = [...document.querySelectorAll('*[id^="device"]')].filter(d => d.checked).map(d => d.value).join();

    const url = new URL(window.location.origin + '/testers');
    url.searchParams.append('countries', countries);
    url.searchParams.append('devices', devices);

    location.href = url.href;
}

function parseQueryParams(queryParams, querySelector, allElement) {
    const elements = [...document.querySelectorAll(querySelector)];
    queryParams?.split(',').forEach(p => {
        const elem = elements.find(e => e.value === p);
        if (elem) {
            elem.checked = true;
        }
    });
    if (elements.every(c => c.checked)) {
        allElement.checked = true;
    }
}

function toggleAllCountries(checked) {
    toggleAll('*[id^="country"]', checked);
}

function toggleAllDevices(checked) {
    toggleAll('*[id^="device"]', checked);
}

function toggleAll(id, checked) {
    [...document.querySelectorAll(id)].forEach(e => e.checked = checked);
}