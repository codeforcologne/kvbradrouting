select 
  number, 
  timeinmillis, 
  distance, 
  modtime, 
  geom
--  ST_ASGEOJSON(ST_Transform(geom,4326)) as geom 
from 
  routing