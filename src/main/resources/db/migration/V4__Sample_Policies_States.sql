-- Ajuste de estados de ejemplo para probar el ciclo de vida de pólizas
-- Se asume que V3__Policy_Lifecycle.sql ya ha creado policy_state y policy_state_id
-- Dejamos UNA póliza representativa por cada estado:
--   id=1 → PENDIENTE
--   id=2 → ACTIVA
--   id=3 → SUSPENDIDA
--   id=4 → CANCELADA
--   id=5 → EXPIRADA

UPDATE policies SET policy_state_id = 1 WHERE id = 1;
UPDATE policies SET policy_state_id = 2 WHERE id = 2;
UPDATE policies SET policy_state_id = 3 WHERE id = 3;
UPDATE policies SET policy_state_id = 4 WHERE id = 4;
UPDATE policies SET policy_state_id = 5 WHERE id = 5;


