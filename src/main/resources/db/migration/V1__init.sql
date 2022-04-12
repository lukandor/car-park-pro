create table carpark.reservation(
    id uuid not null primary key,
    car_id uuid not null,
    spot_id uuid not null,
    creation_time timestamptz,
    expiration_time timestamptz
)
