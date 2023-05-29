import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayTrainers();
});

/**
 * Fetches all users and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayTrainers() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayTrainers(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/trainers', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display users.
 *
 * @param {{trainers: string[]}} trainers
 */
function displayTrainers(trainers) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    trainers.trainers.forEach(trainer => {
        tableBody.appendChild(createTableRow(trainer));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {{name: string, age: number}} trainer
 * @returns {HTMLTableRowElement}
 */
function createTableRow(trainer) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(trainer.name));
    tr.appendChild(createLinkCell('view', '../trainer_view/trainer_view.html?trainer=' + trainer.name));
    tr.appendChild(createButtonCell('delete', () => deleteTrainer(trainer)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {string } trainer to be deleted
 */
function deleteTrainer(trainer) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayTrainers();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/trainers/' + trainer.name, true);
    xhttp.send();
}
