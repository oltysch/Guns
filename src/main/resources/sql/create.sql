CREATE TABLE GUNS (
  ID                          INT PRIMARY KEY,
  MODEL                       VARCHAR(255),
  ORIGIN                      VARCHAR(255),
  HANDY                       VARCHAR(255)/*{'One_handed','Two_handed'}*/,
  FIRING_RANGE                INT,
  EFFECTIVE_FIRING_RANGE      INT,
  CARTRIDGE_CLIP_AVAILABILITY BOOLEAN,
  MATERIAL                    VARCHAR(255)
);