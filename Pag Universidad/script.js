document.addEventListener('DOMContentLoaded', function() {
    // Traductor Universal
    document.getElementById('translate-btn').addEventListener('click', function() {
        let inputText = document.getElementById('input-text').value;
        let sourceLanguage = document.getElementById('source-language').value;
        let targetLanguage = document.getElementById('target-language').value;
        
        if(inputText.trim() === "") {
            document.getElementById('translated-text').textContent = "Por favor ingresa un texto.";
            return;
        }
        
        // Aquí se debería implementar una API de traducción real
        document.getElementById('translated-text').textContent = `Traduciendo de ${sourceLanguage} a ${targetLanguage}: ${inputText}`;
    });

    // Calculadora Ponderada
    let notes = [];
    document.getElementById('add-note-btn').addEventListener('click', function() {
        let nota = parseFloat(document.getElementById('nota').value);
        let porcentaje = parseFloat(document.getElementById('porcentaje').value);
        
        if (isNaN(nota) || isNaN(porcentaje) || porcentaje <= 0 || porcentaje > 100) {
            document.getElementById('added-notes').textContent = "Por favor ingresa valores válidos.";
            return;
        }
        
        notes.push({nota: nota, porcentaje: porcentaje});
        document.getElementById('added-notes').textContent = `Notas agregadas: ${JSON.stringify(notes)}`;
    });

    document.getElementById('calculate-btn').addEventListener('click', function() {
        let total = 0;
        let totalPercentage = 0;

        notes.forEach(function(note) {
            total += note.nota * (note.porcentaje / 100);
            totalPercentage += note.porcentaje;
        });

        let average = total / totalPercentage * 100;
        document.getElementById('average-result').textContent = `Promedio: ${average.toFixed(2)}`;
    });

    // Conversor de Divisas
    document.getElementById('convert-btn').addEventListener('click', function() {
        let amount = parseFloat(document.getElementById('amount').value);
        let currencyFrom = document.getElementById('currency-from').value;
        let currencyTo = document.getElementById('currency-to').value;
        
        if (isNaN(amount)) {
            document.getElementById('converted-amount').textContent = "Por favor ingresa una cantidad válida.";
            return;
        }
        
        // Aquí se debe conectar a una API real de conversión de divisas
        document.getElementById('converted-amount').textContent = `${amount} ${currencyFrom} = ${(amount * 1.1).toFixed(2)} ${currencyTo}`;
    });

    // Calculadora IMC
    document.getElementById('calculate-imc-btn').addEventListener('click', function() {
        let weight = parseFloat(document.getElementById('weight').value);
        let height = parseFloat(document.getElementById('height').value);
        
        if (isNaN(weight) || isNaN(height) || height <= 0 || weight <= 0) {
            document.getElementById('imc-result').textContent = "Por favor ingresa valores válidos.";
            return;
        }
        
        let imc = weight / (height * height);
        document.getElementById('imc-result').textContent = `IMC: ${imc.toFixed(2)}`;
    });

    // Conversor de Temperatura
    document.getElementById('convert-temp-btn').addEventListener('click', function() {
        let temperature = parseFloat(document.getElementById('temperature').value);
        let tempFrom = document.getElementById('temp-from').value;
        let tempTo = document.getElementById('temp-to').value;
        
        if (isNaN(temperature)) {
            document.getElementById('temp-result').textContent = "Por favor ingresa una temperatura válida.";
            return;
        }
        
        let result = temperature;
        
        if (tempFrom === 'Celsius' && tempTo === 'Fahrenheit') {
            result = (temperature * 9/5) + 32;
        } else if (tempFrom === 'Celsius' && tempTo === 'Kelvin') {
            result = temperature + 273.15;
        } else if (tempFrom === 'Fahrenheit' && tempTo === 'Celsius') {
            result = (temperature - 32) * 5/9;
        } else if (tempFrom === 'Fahrenheit' && tempTo === 'Kelvin') {
            result = ((temperature - 32) * 5/9) + 273.15;
        } else if (tempFrom === 'Kelvin' && tempTo === 'Celsius') {
            result = temperature - 273.15;
        } else if (tempFrom === 'Kelvin' && tempTo === 'Fahrenheit') {
            result = ((temperature - 273.15) * 9/5) + 32;
        }
        
        document.getElementById('temp-result').textContent = `${temperature} ${tempFrom} = ${result.toFixed(2)} ${tempTo}`;
    });

    // Generador de Contraseñas
    document.getElementById('generate-password-btn').addEventListener('click', function() {
        let length = parseInt(document.getElementById('password-length').value);
        
        if (isNaN(length) || length <= 0) {
            document.getElementById('password-result').textContent = "Por favor ingresa una longitud válida.";
            return;
        }
        
        let characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()';
        let password = '';
        
        for (let i = 0; i < length; i++) {
            password += characters.charAt(Math.floor(Math.random() * characters.length));
        }
        
        document.getElementById('password-result').textContent = `Contraseña generada: ${password}`;
    });

    // Calculadora de Intereses
    document.getElementById('calculate-interest-btn').addEventListener('click', function() {
        let principal = parseFloat(document.getElementById('principal').value);
        let rate = parseFloat(document.getElementById('rate').value);
        let time = parseFloat(document.getElementById('time').value);
        
        if (isNaN(principal) || isNaN(rate) || isNaN(time)) {
            document.getElementById('interest-result').textContent = "Por favor ingresa valores válidos.";
            return;
        }
        
        let interest = principal * rate * time / 100;
        document.getElementById('interest-result').textContent = `Intereses: ${interest.toFixed(2)}`;
    });

    // Planificador de Tareas
    document.getElementById('add-task-btn').addEventListener('click', function() {
        let taskName = document.getElementById('task-name').value;
        let dueDate = document.getElementById('due-date').value;
        let taskDescription = document.getElementById('task-description').value;
        
        if (taskName.trim() === "") {
            document.getElementById('task-list').textContent = "Por favor ingresa el nombre de la tarea.";
            return;
        }
        
        let task = `<strong>${taskName}</strong> - ${dueDate}: ${taskDescription}`;
        document.getElementById('task-list').innerHTML += `<div>${task}</div>`;
    });

    // Conversor de Peso
    document.getElementById('convert-weight-btn').addEventListener('click', function() {
        let weight = parseFloat(document.getElementById('weight-converter').value);
        let weightFrom = document.getElementById('weight-from').value;
        let weightTo = document.getElementById('weight-to').value;
        
        if (isNaN(weight)) {
            document.getElementById('converted-weight').textContent = "Por favor ingresa un peso válido.";
            return;
        }
        
        let result = weight;
        
        if (weightFrom === 'kg' && weightTo === 'lb') {
            result = weight * 2.20462;
        } else if (weightFrom === 'kg' && weightTo === 'oz') {
            result = weight * 35.274;
        } else if (weightFrom === 'lb' && weightTo === 'kg') {
            result = weight / 2.20462;
        } else if (weightFrom === 'lb' && weightTo === 'oz') {
            result = weight * 16;
        } else if (weightFrom === 'oz' && weightTo === 'kg') {
            result = weight / 35.274;
        } else if (weightFrom === 'oz' && weightTo === 'lb') {
            result = weight / 16;
        }
        
        document.getElementById('converted-weight').textContent = `${weight} ${weightFrom} = ${result.toFixed(2)} ${weightTo}`;
    });

    // Temporizador de Estudio
    let timerInterval;
    document.getElementById('start-timer-btn').addEventListener('click', function() {
        let studyTime = parseInt(document.getElementById('study-time').value);
        
        if (isNaN(studyTime) || studyTime <= 0) {
            document.getElementById('timer-display').textContent = "Por favor ingresa un tiempo válido.";
            return;
        }
        
        let timeRemaining = studyTime * 60;
        
        timerInterval = setInterval(function() {
            let minutes = Math.floor(timeRemaining / 60);
            let seconds = timeRemaining % 60;
            document.getElementById('timer-display').textContent = `${minutes}:${seconds < 10 ? '0' + seconds : seconds}`;
            timeRemaining--;
            
            if (timeRemaining < 0) {
                clearInterval(timerInterval);
                document.getElementById('timer-display').textContent = "Tiempo de estudio terminado!";
            }
        }, 1000);
    });

    // Conversor de Unidades de Distancia
    document.getElementById('convert-distance-btn').addEventListener('click', function() {
        let distance = parseFloat(document.getElementById('distance').value);
        let distanceFrom = document.getElementById('distance-from').value;
        let distanceTo = document.getElementById('distance-to').value;
        
        if (isNaN(distance)) {
            document.getElementById('converted-distance').textContent = "Por favor ingresa una distancia válida.";
            return;
        }
        
        let result = distance;
        
        if (distanceFrom === 'km' && distanceTo === 'm') {
            result = distance * 1000;
        } else if (distanceFrom === 'km' && distanceTo === 'mi') {
            result = distance * 0.621371;
        } else if (distanceFrom === 'm' && distanceTo === 'km') {
            result = distance / 1000;
        } else if (distanceFrom === 'm' && distanceTo === 'mi') {
            result = distance * 0.000621371;
        } else if (distanceFrom === 'mi' && distanceTo === 'km') {
            result = distance / 0.621371;
        } else if (distanceFrom === 'mi' && distanceTo === 'm') {
            result = distance / 0.000621371;
        }
        
        document.getElementById('converted-distance').textContent = `${distance} ${distanceFrom} = ${result.toFixed(2)} ${distanceTo}`;
    });
});
function redirectTo(page) {
    window.location.href = page;
}
