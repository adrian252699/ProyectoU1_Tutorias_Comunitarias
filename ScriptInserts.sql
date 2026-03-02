USE tutoriascomunitarias;

-- =========================
-- INSERT TUTORES
-- =========================
INSERT INTO tutor (nombre, especialidad, telefono, correo) VALUES
('Carlos Martínez', 'Matemáticas', '6441234567', 'carlos.martinez@email.com'),
('Ana López', 'Lengua y Literatura', '6442345678', 'ana.lopez@email.com'),
('José Ramírez', 'Física', '6443456789', 'jose.ramirez@email.com');

-- =========================
-- INSERT ESTUDIANTES
-- =========================
INSERT INTO estudiante (nombre, grado_escolar, escuela_procedencia, telefono_contacto, fecha_nacimiento) VALUES
('María Torres', '3° Secundaria', 'Secundaria Técnica #5', '6444567890', '2009-05-14'),
('Luis Hernández', '2° Secundaria', 'Secundaria Federal #2', '6445678901', '2010-08-21'),
('Sofía Navarro', '1° Preparatoria', 'Preparatoria CBTIS 37', '6446789012', '2008-02-10'),
('Diego Morales', '6° Primaria', 'Primaria Benito Juárez', '6447890123', '2012-11-03');

-- =========================
-- INSERT MATERIAS
-- =========================
INSERT INTO materia (nombre, nivel_educativo, descripcion) VALUES
('Álgebra', 'Secundaria', 'Resolución de ecuaciones y expresiones algebraicas'),
('Comprensión Lectora', 'Secundaria', 'Análisis y comprensión de textos'),
('Física Básica', 'Preparatoria', 'Conceptos fundamentales de física'),
('Matemáticas Básicas', 'Primaria', 'Operaciones aritméticas fundamentales');

-- =========================
-- INSERT DISPONIBILIDAD
-- =========================
INSERT INTO disponibilidad (dia, hora_inicio, hora_fin, id_tutor) VALUES
('2026-03-05', '09:00:00', '12:00:00', 1),
('2026-03-06', '10:00:00', '13:00:00', 2),
('2026-03-07', '08:00:00', '11:00:00', 3);

-- =========================
-- INSERT SESIONES DE TUTORIA
-- =========================
INSERT INTO sesiontutoria (fecha, hora, estado_sesion, id_tutor, id_estudiante, id_materia) VALUES
('2026-03-05', '09:30:00', 'programada', 1, 1, 1),
('2026-03-06', '10:30:00', 'en curso', 2, 2, 2),
('2026-03-07', '08:30:00', 'completada', 3, 3, 3),
('2026-03-05', '11:00:00', 'programada', 1, 4, 4);