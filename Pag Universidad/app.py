from flask import Flask, request, send_file
from pdf2docx import Converter
from PIL import Image
import os

app = Flask(__name__)

# Asegurarse de tener un directorio para almacenar los archivos temporales
UPLOAD_FOLDER = 'uploads'
os.makedirs(UPLOAD_FOLDER, exist_ok=True)
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

# Convertir PDF a Word
@app.route('/convert_pdf', methods=['POST'])
def convert_pdf():
    file = request.files['file']
    pdf_path = os.path.join(app.config['UPLOAD_FOLDER'], 'temp.pdf')
    file.save(pdf_path)
    
    word_path = os.path.join(app.config['UPLOAD_FOLDER'], 'temp.docx')
    cv = Converter(pdf_path)
    cv.convert(word_path, start=0, end=None)
    return send_file(word_path, as_attachment=True)

# Convertir imagen (por ejemplo, de JPEG a PNG)
@app.route('/convert_image', methods=['POST'])
def convert_image():
    file = request.files['file']
    image = Image.open(file)
    new_image_path = os.path.join(app.config['UPLOAD_FOLDER'], 'converted_image.png')
    image.save(new_image_path, 'PNG')
    return send_file(new_image_path, as_attachment=True)

if __name__ == '__main__':
    app.run(debug=True)
