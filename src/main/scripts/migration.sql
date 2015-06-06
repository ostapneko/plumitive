CREATE VIRTUAL TABLE documents USING fts4(id,title,scanned_text,sender,recipients,year,month,tags,tokenize=porter);
