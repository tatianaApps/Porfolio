//
//  TableViewController.swift
//  frameworks
//
//  Created by APPS2T on 22/11/21.
//

import UIKit

class TableViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    
    @IBOutlet var tableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        self.title = "🍎 Frameworks"
        tableView.dataSource = self
        tableView.delegate = self
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return MockData.frameworks.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if let cell = tableView.dequeueReusableCell(withIdentifier: "FrameworkCellId", for: indexPath) as? FrameworkCell {
            cell.framework = MockData.frameworks[indexPath.row]
            return cell
        }
        else {
            return UITableViewCell()
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        if let detailVC = storyboard?.instantiateViewController(identifier: "DetailVCId") as? DetailViewController {
            detailVC.framework = MockData.frameworks[indexPath.row]
            navigationController?.pushViewController(detailVC, animated: true)
        }
    }
}


