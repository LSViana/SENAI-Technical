$(document).ready(function () {
    var notes = [];
    var notesKey = "app-notes";
    var notesArea = $("#notes-area");
    var saveNote = $("#save-note");
    // Initializing local variables
    var noteTitle = $("#note-data input[name=\"note-title\"]");
    var noteText = $("#note-data input[name=\"note-text\"]");
    // Recovery data from local storage
    var notesJSON = window.localStorage.getItem(notesKey);
    if (notesJSON) {
        notes = JSON.parse(notesJSON);
        loadNotes(notes);
    }
    // Apply all delegates
    // Save Notes
    $(saveNote).on("click", function (event) {
        var note = {
            title: $(noteTitle).val(),
            text: $(noteText).val()
        };
        addNote(note);
        notes.push(note);
        // Save notes to Local Storage
        window.localStorage.setItem(notesKey, JSON.stringify(notes));
        //
        clearFields();
    });
    // Applying context menus
    var notesElements = document.querySelectorAll("#notes-area div");
    Array.prototype.forEach.call(notesElements, function(element, index, array) {
        element.addEventListener("contextmenu", function(event) {
            event.preventDefault();
            console.log(event);
            // Make the menu appear and show the options
        });
    });
    // Edit a note
    Array.prototype.forEach.call(notesElements, function(element, index, array) {
        element.addEventListener("click", function(event) {
            
        });
    });
    // Business functions
    function clearFields() {
        $("#note-data input").val("");
    }

    function loadNotes(notes) {
        notes.forEach(note => {
            addNote(note);
        });
    }

    function addNote(note) {
        let noteElement = $("<div>");
        noteElement.append($("<h6>").text(note.title));
        noteElement.append($("<p>").text(note.text));
        notesArea.append(noteElement);
    }
});