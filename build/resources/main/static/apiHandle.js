const url = '/quotes'
async function fetchData(){
  try{
  let res = await fetch(url);
  return await res.json();
  }catch(error){
  console.log(error);
  }

}
async function quoteData(){
  let quotes = await fetchData();
  let html = '';

  quotes.forEach(quote => {
    let htmlSegment = `<div class="quote">
                        <h2>${quote.quoteName} ${quote.quoteDescription}</h2>
                        </div>`;

                        html += htmlSegment;
  });

  let container = document.querySelector('#container');
  container.innerHTML = html;
}

$('#test_api').on('click', quoteData);