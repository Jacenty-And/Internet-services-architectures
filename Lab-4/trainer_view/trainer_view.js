import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayTrainer();
    fetchAndDisplayPokemons();
    addButton();
});

/**
 * Fetches all users and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayPokemons() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayPokemons(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/trainers/' + getParameterByName('trainer') + '/pokemons', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display characters.
 *
 * @param {{pokemons: {id: number, name:string}[]}} pokemons
 */
function displayPokemons(pokemons) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    pokemons.pokemons.forEach(pokemon => {
        tableBody.appendChild(createTableRow(pokemon));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {{id: number, name: string}} pokemon``
 * @returns {HTMLTableRowElement}
 */
function createTableRow(pokemon) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(pokemon.name));
    tr.appendChild(createLinkCell('edit', '../pokemon_edit/pokemon_edit.html?trainer='
        + getParameterByName('trainer') + '&pokemon=' + pokemon.id));
    tr.appendChild(createButtonCell('delete', () => deletePokemon(pokemon.id)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {number} pokemon to be deleted
 */
function deletePokemon(pokemon) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayPokemons();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/pokemons/' + pokemon, true);
    xhttp.send();
}


/**
 * Fetches single user and modifies the DOM tree in order to display it.
 */
function fetchAndDisplayTrainer() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayTrainer(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/trainers/' + getParameterByName('trainer'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display user.
 *
 * @param {{name: string, age: number}} trainer
 */
function displayTrainer(trainer) {
    setTextNode('name', trainer.name);
    setTextNode('age', trainer.age);
}

function addButton() {
    const button = document.getElementById("add");
    const link = '../pokemon_add/pokemon_add.html?trainer=' + getParameterByName('trainer');
    button.href = link;
}