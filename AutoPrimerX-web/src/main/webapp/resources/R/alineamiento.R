#source("http://www.bioconductor.org/biocLite.R")
#biocLite("msa")
print(iconv(x = 'c:/Users/JAno/Documents/Universidad/Bioinformática/AutoPrimerX-CeBiB-/AutoPrimerX-web/src/main/webapp/resources/R/', from = "UTF-8", to = "UTF-8"))
setwd(iconv(x = 'c:/Users/JAno/Documents/Universidad/Bioinformática/AutoPrimerX-CeBiB-/AutoPrimerX-web/src/main/webapp/resources/R/', from = "UTF-8", to = "UTF-8"))
library(msa)
#mySequenceFile <- system.file("completo.fasta", package="msa")
#db = file.choose()
args = commandArgs(trailingOnly = TRUE)
#mySequences <- readAAStringSet(db)
tryCatch({
  mySequences = readAAStringSet(args[1])
}, error = function(err){
  write(paste("ERROR: ", err), file = "error.txt")
})
myFirstAlignment = msa(mySequences)
#msaPrettyPrint(myFirstAlignment, output="pdf", showNames="none",
#               showLogo="none", askForOverwrite=FALSE, verbose=FALSE)
#myClustalWAlignment <- msa(mySequences, "ClustalW")
#print(myFirstAlignment, showConsensus=FALSE, halfNrow=3)
matrix = as.matrix(myFirstAlignment)
rows = nrow(matrix)
i = 1
output = c()
while(i <= rows){
  output = c(output, paste(matrix[i,], collapse = ""))
  i=i+1
}
write(output, file = "alineamiento")
