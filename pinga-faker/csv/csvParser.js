const parseRow = (row, parser) => {
  return parser(row);
};

const parseCsv = (contents, parser) => {
  return contents.split('\r\n')
    .map(row => parseRow(row.split(','), parser));
};

module.exports = { parseCsv };
