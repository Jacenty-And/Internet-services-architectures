import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateInfoAction(event));
    backButton();
});

/**
 * Action event handled for updating character info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/pokemons', true);

    const request = {
        'name': document.getElementById('name').value,
        'level': parseInt(document.getElementById('level').value),
        'trainer': getParameterByName('trainer')
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}

function backButton() {
    const button = document.getElementById("back");
    const link = '../trainer_view/trainer_view.html?trainer=' + getParameterByName('trainer');
    button.href = link;
}