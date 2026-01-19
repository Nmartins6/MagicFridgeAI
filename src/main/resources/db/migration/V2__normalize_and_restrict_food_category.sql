-- normalize existing category values ('Fruit' -> 'FRUIT'_
UPDATE food_item
SET category = UPPER(category);

-- fix unexpected or invalid category values
-- not define values will be set to 'OTHER'
UPDATE food_item
SET category = 'OTHER'
WHERE category NOT IN (
        'FRUIT',
        'VEGETABLE',
        'MEAT',
        'DAIRY',
        'GRAIN',
        'BEVERAGE',
        'OTHER'
);

-- Esure the category column does not allow NULL values
ALTER TABLE food_item
ALTER COLUMN category SET NOT NULL;

-- Restrict allowed category values (enum-like constraint at database level)
ALTER TABLE food_item
ADD CONSTRAINT chk_food_item_category
CHECK (category IN (
    'FRUIT',
    'VEGETABLE',
    'MEAT',
    'DAIRY',
    'GRAIN',
    'BEVERAGE',
    'OTHER'
));