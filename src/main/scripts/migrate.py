#!/usr/bin/env python

import sqlite3
import os
import re

migration_dir = os.path.dirname(os.path.realpath(__file__))
resource_dir = os.path.join(migration_dir, '..', 'resources')
sqlite_db_line = open(resource_dir + '/sqlite_db_path.conf', 'r').read()

db_path = re.findall("plumitive.sqliteDB.dbPath\s*=\s*\"(.*)\"", sqlite_db_line)[0]

qry = open(migration_dir + '/migration.sql', 'r').read()

# Create and connect to the database
print("Attempt to create the database at " + db_path)
conn = sqlite3.connect(db_path)
print("Created plumitive database")

#Create cursor
c = conn.cursor()

# Create table
c.execute(qry)
print("Created documents table")

# Save (commit) the changes
conn.commit()

# Close cursor and connection
c.close()
conn.close()
print("Closed the database connection")
