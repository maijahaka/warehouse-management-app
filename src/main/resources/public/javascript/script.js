function showAllProducts(url) {
  const tbody = document.getElementById("products")

  fetch(url)
    .then(response => {
      if (!response.ok) {
        throw Error(response.statusText)
      }
      return response
    })
    .then(response => response.json())
    .then(data => {
      console.log(data)
      return data
    })
    .then(data => {
      let products = data
      products.map(function(product) {
        let tr = document.createElement("tr"), 
            th = document.createElement("th"),
            tdName = document.createElement("td"),
            tdColor = document.createElement("td"),
            tdPrice = document.createElement("td"),
            tdManufacturer = document.createElement("td"),
            spanId = document.createElement("span"),
            spanName = document.createElement("span"),
            spanColor = document.createElement("span"),
            spanPrice = document.createElement("span"),
            spanManufacturer = document.createElement("span")
        spanId.innerHTML = product.id
        spanName.innerHTML = product.name
        const numberOfColors = product.color.length
        var colors = ""
        var i
        for (i = 0; i < numberOfColors - 1; i++) {
          colors += `${product.color[i]}, `
        }
        colors += product.color[numberOfColors - 1]
        spanColor.innerHTML = colors
        spanPrice.innerHTML = `${product.price} €`
        spanManufacturer.innerHTML = product.manufacturer
        th.appendChild(spanId)
        tdName.appendChild(spanName)
        tdColor.appendChild(spanColor)
        tdPrice.appendChild(spanPrice)
        tdManufacturer.appendChild(spanManufacturer)
        tr.appendChild(th)
        tr.appendChild(tdName)
        tr.appendChild(tdColor)
        tr.appendChild(tdPrice)
        tr.appendChild(tdManufacturer)
        tbody.appendChild(tr)
      })
      document.getElementById("loader").style.display="none"
    })
    .catch(error => {
      console.log("Error:", error)
      let errorMessage = document.getElementById("errorMessage")
      errorMessage.innerHTML = "<div class='alert alert-danger' role='alert'>"
        + "<strong>Error:</strong> Data could not be retrieved. Please contact system administrator."
        + "</div>"
    })
}